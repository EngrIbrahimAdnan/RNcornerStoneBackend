package RNcornerStoneBackend.RNcornerStoneBackend.Chore.Service;

import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Entity.ChoreEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Entity.Status;
import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Repository.ChoreRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.Chore.bo.ChoreResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.User.repository.UserRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.child.Repository.ChildRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.child.entity.ChildEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChoreService {


    private final ChoreRepository choreRepository;
    private final UserRepository userRepository;
    private final ChildRepository childRepository;

    public ChoreService(ChoreRepository choreRepository, UserRepository userRepository, ChildRepository childRepository) {
        this.choreRepository = choreRepository;
        this.userRepository = userRepository;
        this.childRepository = childRepository;
    }


    public ChoreResponse createChore(Long parentId, Long childId, ChoreResponse choreDTO) {
        UserEntity parent = userRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));
        ChildEntity child = childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        ChoreEntity chore = new ChoreEntity();
        chore.setParent(parent);
        chore.setChild(child);
        chore.setTitle(choreDTO.getTitle());
        chore.setDescription(choreDTO.getDescription());
        chore.setRewardsAmount(choreDTO.getRewardAmount());
        chore.setStatus(Status.PENDING);

        ChoreEntity savedChore = choreRepository.save(chore);

        ChoreResponse savedChoreResponse = new ChoreResponse();
        savedChoreResponse.setId(savedChore.getId());
        savedChoreResponse.setParentId(savedChore.getParent().getId());
        savedChoreResponse.setChildId(savedChore.getChild().getId());
        savedChoreResponse.setTitle(savedChore.getTitle());
        savedChoreResponse.setDescription(savedChore.getDescription());
        savedChoreResponse.setRewardAmount(savedChore.getRewardsAmount());
        savedChoreResponse.setStatus(savedChore.getStatus());

        return savedChoreResponse;

    }

    public ChoreResponse updateChoreStatus(Long choreId, Long parentId, Status newStatus) {
        ChoreEntity chore = choreRepository.findById(choreId)
                .orElseThrow(() -> new EntityNotFoundException("Chore not found"));

        UserEntity parent = userRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));

        if (!chore.getParent().getId().equals(parentId)) {
            throw new IllegalArgumentException("Only the assigned parent can update the chore status");
        }

        if (newStatus != Status.PENDING && newStatus != Status.COMPLETED && newStatus != Status.UNCOMPLETED) {
            throw new IllegalArgumentException("Invalid status. Must be PENDING, COMPLETED, or UNCOMPLETED");
        }

        chore.setStatus(newStatus);
        ChoreEntity updatedChore = choreRepository.save(chore);

        ChoreResponse updatedChoreResponse = new ChoreResponse();
        updatedChoreResponse.setId(updatedChore.getId());
        updatedChoreResponse.setParentId(updatedChore.getParent().getId());
        updatedChoreResponse.setChildId(updatedChore.getChild().getId());
        updatedChoreResponse.setTitle(updatedChore.getTitle());
        updatedChoreResponse.setDescription(updatedChore.getDescription());
        updatedChoreResponse.setRewardAmount(updatedChore.getRewardsAmount());
        updatedChoreResponse.setStatus(updatedChore.getStatus());

        return updatedChoreResponse;
    }
}
