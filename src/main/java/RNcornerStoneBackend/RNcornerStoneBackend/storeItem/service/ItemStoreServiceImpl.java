package RNcornerStoneBackend.RNcornerStoneBackend.storeItem.service;

import RNcornerStoneBackend.RNcornerStoneBackend.child.ChildService.ChildService;
import RNcornerStoneBackend.RNcornerStoneBackend.child.entity.ChildEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.BuyStoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.CreateStoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.entity.StoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.repository.StoreItemRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.Role;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ItemStoreServiceImpl implements ItemStoreService {
    private final StoreItemRepository storeItemRepository;
    private final ChildService childService;


    public ItemStoreServiceImpl(StoreItemRepository storeItemRepository,
                                ChildService childService) {
        this.storeItemRepository = storeItemRepository;
        this.childService = childService;
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
        }
        else{
            return "child cannot add store items. Please login as Parent";
        }
    }

    // for child
    public String buyStoreItem(BuyStoreItemEntity request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        if (user.getRole() == Role.CHILD) {
            StoreItemEntity storeItemEntity = storeItemRepository.findByChildUserEntity(user).get();
            Date currentDate = new Date();
            storeItemEntity.setPurchasedAt(currentDate);
            storeItemRepository.save(storeItemEntity);
            return null;

        }else {
            return "Parent cannot buy Store item. Please login as child.";
        }
    }
}
