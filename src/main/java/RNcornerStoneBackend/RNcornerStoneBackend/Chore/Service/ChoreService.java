//package RNcornerStoneBackend.RNcornerStoneBackend.Chore.Service;
//
//import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Entity.ChoreEntity;
//import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Entity.Status;
//import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Repository.ChoreRepository;
//import RNcornerStoneBackend.RNcornerStoneBackend.Chore.bo.ChoreResponse;
//import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
//import RNcornerStoneBackend.RNcornerStoneBackend.User.repository.UserRepository;
//import RNcornerStoneBackend.RNcornerStoneBackend.child.Repository.ChildRepository;
//import RNcornerStoneBackend.RNcornerStoneBackend.child.entity.ChildEntity;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ChoreService {
//
//
//    private final ChoreRepository choreRepository;
//    private final UserRepository userRepository;
//    private final ChildRepository childRepository;
//
//    public ChoreService(ChoreRepository choreRepository, UserRepository userRepository, ChildRepository childRepository) {
//        this.choreRepository = choreRepository;
//        this.userRepository = userRepository;
//        this.childRepository = childRepository;
//    }
//
//    public ChoreResponse createChore(ChoreResponse choreDTO) {
//        UserEntity parent = userRepository.findById(choreDTO.getParentId())
//                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));
//        UserEntity child = userRepository.findById(choreDTO.getChildId())
//                .orElseThrow(() -> new EntityNotFoundException("Child not found"));
//
//        ChoreEntity chore = new ChoreEntity();
//        chore.setParent(parent);
//        chore.setChild(child);
//        chore.setTitle(choreDTO.getTitle());
//        chore.setDescription(choreDTO.getDescription());
//        chore.setRewardsAmount(choreDTO.getRewardAmount());
//        chore.setStatus(Status.PENDING);
//
//        ChoreEntity savedChore = choreRepository.save(chore);
//
//        ChoreResponse savedChoreDTO = new ChoreResponse();
//        savedChoreDTO.setId(savedChore.getId());
//        savedChoreDTO.setParentId(savedChore.getParent().getId());
//        savedChoreDTO.setChildId(savedChore.getChild().getId());
//        savedChoreDTO.setTitle(savedChore.getTitle());
//        savedChoreDTO.setDescription(savedChore.getDescription());
//        savedChoreDTO.setRewardAmount(savedChore.getRewardsAmount());
//        savedChoreDTO.setStatus(savedChore.getStatus());
//
//        return savedChoreDTO;
//    }
//
//    public List<ChoreResponse> getChoresByChild(Long childId) {
//        return choreRepository.findByChildId(childId).stream()
//                .map(chore -> {
//                    ChoreResponse dto = new ChoreResponse();
//                    dto.setId(chore.getId());
//                    dto.setParentId(chore.getParent().getId());
//                    dto.setChildId(chore.getChild().getId());
//                    dto.setTitle(chore.getTitle());
//                    dto.setDescription(chore.getDescription());
//                    dto.setRewardAmount(chore.getRewardsAmount());
//                    dto.setStatus(chore.getStatus());
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }
//
//    public ChoreResponse updateChoreStatus(Long choreId, Status status) {
//        ChoreEntity chore = choreRepository.findById(choreId)
//                .orElseThrow(() -> new EntityNotFoundException("Chore not found"));
//
//        chore.setStatus(status);
//
//        if (status == Status.COMPLETED) {
//            UserEntity child = chore.getChild();
//            child.setBalance(child.getBalance() + chore.getRewardsAmount());
//            childRepository.save(child);
//        }
//
//        ChoreEntity updatedChore = choreRepository.save(chore);
//
//        ChoreResponse updatedChoreDTO = new ChoreResponse();
//        updatedChoreDTO.setId(updatedChore.getId());
//        updatedChoreDTO.setParentId(updatedChore.getParent().getId());
//        updatedChoreDTO.setChildId(updatedChore.getChild().getId());
//        updatedChoreDTO.setTitle(updatedChore.getTitle());
//        updatedChoreDTO.setDescription(updatedChore.getDescription());
//        updatedChoreDTO.setRewardAmount(updatedChore.getRewardsAmount());
//        updatedChoreDTO.setStatus(updatedChore.getStatus());
//
//        return updatedChoreDTO;
//    }
//
//}
