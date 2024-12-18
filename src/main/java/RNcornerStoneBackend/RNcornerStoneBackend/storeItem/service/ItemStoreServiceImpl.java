package RNcornerStoneBackend.RNcornerStoneBackend.storeItem.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import RNcornerStoneBackend.RNcornerStoneBackend.child.ChildService.ChildService;
import RNcornerStoneBackend.RNcornerStoneBackend.child.entity.ChildEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.BuyStoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.CreateStoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.StoreItemResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.entity.StoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.repository.StoreItemRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.Role;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemStoreServiceImpl implements ItemStoreService {
    private final StoreItemRepository storeItemRepository;
    private final ChildService childService;

    public ItemStoreServiceImpl(StoreItemRepository storeItemRepository,
            ChildService childService) {
        this.storeItemRepository = storeItemRepository;
        this.childService = childService;
    }

    @Override
    public List<StoreItemResponse> getCurrentChildStoreItems() {
        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        // Ensure the user is a child
        if (user.getRole() != Role.CHILD) {
            throw new RuntimeException("Only children can access their store items");
        }

        // Get all store items for the child
        List<StoreItemEntity> storeItems = storeItemRepository.findAllByChildUserEntity(user);

        // Convert to response objects
        return storeItems.stream()
                .map(this::convertToStoreItemResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StoreItemResponse> getStoreItemsForParentChild(Long childId) {
        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity parent = (UserEntity) authentication.getPrincipal();

        // Ensure the user is a parent
        if (parent.getRole() != Role.PARENT) {
            throw new RuntimeException("Only parents can access their children's store items");
        }

        // Find the child and verify it belongs to the parent
        ChildEntity child = childService.getChildEntityById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        if (!child.getParent().getId().equals(parent.getId())) {
            throw new RuntimeException("You can only access store items for your own children");
        }

        // Get all store items for the child
        List<StoreItemEntity> storeItems = storeItemRepository.findAllByChildUserEntity(child.getUser());

        // Convert to response objects
        return storeItems.stream()
                .map(this::convertToStoreItemResponse)
                .collect(Collectors.toList());
    }

    // for parent
    public String addStoreItem(CreateStoreItemEntity request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        if (user.getRole() == Role.PARENT) {
            // Get child entity from the 'child id' from 'request'
            ChildEntity childEntity = childService.getChildEntityById(request.getChild_id()).get();

            StoreItemEntity storeItemEntity = new StoreItemEntity();
            storeItemEntity.setName(request.getName());
            storeItemEntity.setDescription(request.getDescription());
            storeItemEntity.setPrice(request.getPrice());
            storeItemEntity.setImage(request.getImage());
            storeItemEntity.setParentUserEntity(user);
            storeItemEntity.setChildUserEntity(childEntity.getUser());
            storeItemEntity.setPurchasedAt(null);// null translates to not bought by child
            storeItemRepository.save(storeItemEntity);
            return null;
        } else {
            return "child cannot add store items. Please login as Parent";
        }
    }

    // for child
    public String buyStoreItem(BuyStoreItemEntity request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        if (user.getRole() != Role.CHILD) {
            return "Parent cannot buy Store item. Please login as child.";
        }

        // Find the store item by ID
        StoreItemEntity storeItem = storeItemRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Store item not found"));

        // Verify the item belongs to the current child
        if (!storeItem.getChildUserEntity().getId().equals(user.getId())) {
            return "This store item does not belong to you";
        }

        // Check if the item is already purchased
        if (storeItem.getPurchasedAt() != null) {
            return "This item has already been purchased";
        }

        ChildEntity childEntity= childService.getChildEntityByUser(user);

        if (childEntity.getBalance()-storeItem.getPrice()<0){
            return "Not enough balance available to buy";
        }

        childService.updateBalnce(childEntity, childEntity.getBalance() - storeItem.getPrice());

        // Update purchase date
        storeItem.setPurchasedAt(new Date());
        storeItemRepository.save(storeItem);

        return null;
    }

    private StoreItemResponse convertToStoreItemResponse(StoreItemEntity storeItem) {
        StoreItemResponse response = new StoreItemResponse();
        response.setId(storeItem.getId());
        response.setName(storeItem.getName());
        response.setDescription(storeItem.getDescription());
        response.setPrice(storeItem.getPrice());
        response.setImage(storeItem.getImage());
        response.setParentId(storeItem.getParentUserEntity().getId());
        response.setChildId(storeItem.getChildUserEntity().getId());
        response.setPurchasedAt(storeItem.getPurchasedAt());
        return response;
    }
}
