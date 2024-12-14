package RNcornerStoneBackend.RNcornerStoneBackend.child.ChildService;

import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.Role;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.User.repository.UserRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.child.Repository.ChildRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.child.bo.AddChildRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.child.bo.ChildResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.child.entity.ChildEntity;
import org.springframework.stereotype.Service;

@Service
public class ChildService {
    private final ChildRepository childRepository;
    private final UserRepository userRepository;

    public ChildService(ChildRepository childRepository, UserRepository userRepository) {
        this.childRepository = childRepository;
        this.userRepository = userRepository;
    }


    public ChildResponse addChild(Long parentId, AddChildRequest request) {
        UserEntity parent = userRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Parent not found"));

        if (parent.getRole() != Role.PARENT) {
            throw new RuntimeException("Only parents can add children");
        }

        if (request.getUsername() == null || request.getEmail() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Username, email, and password cannot be null");
        }

        UserEntity childUser = new UserEntity();
        childUser.setUsername(request.getUsername());
        childUser.setEmail(request.getEmail());
        childUser.setRole(Role.CHILD);
        childUser.setPassword(request.getPassword());

        UserEntity savedChildUser = userRepository.save(childUser);

        ChildEntity child = new ChildEntity();
                    child.setUser(savedChildUser);
                    child.setParent(parent);
                    child.setBalance(request.getInitialBalance());
                    child.setDateOfBirth(request.getDateOfBirth());
                    child.setEmail(request.getEmail());
                    child.setUsername(request.getUsername());


        ChildEntity savedChild = childRepository.save(child);

        ChildResponse response = new ChildResponse( );
        response.setId(child.getId());
        response.setBalance(child.getBalance());
        response.setDateOfBirth(child.getDateOfBirth());
        response.setParentId(child.getParent().getId());
        response.setUsername(childUser.getUsername());
        response.setEmail(childUser.getEmail());


        return response;
    }
}
