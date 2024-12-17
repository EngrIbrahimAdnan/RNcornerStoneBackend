package RNcornerStoneBackend.RNcornerStoneBackend.child.ChildService;

import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.Role;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.repository.UserRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.child.Repository.ChildRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.child.bo.AddChildRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.child.bo.ChildResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.child.entity.ChildEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

@Service
public class ChildService {
    private final ChildRepository childRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ChildService(ChildRepository childRepository, UserRepository userRepository,  PasswordEncoder passwordEncoder
) {

        this.childRepository = childRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


//    public ChildResponse addChild(Long parentId, AddChildRequest request) {
//        UserEntity parent = userRepository.findById(parentId)
//                .orElseThrow(() -> new RuntimeException("Parent not found"));
//
//        if (parent.getRole() != Role.PARENT) {
//            throw new RuntimeException("Only parents can add children");
//        }
//
//        if (request.getUsername() == null || request.getEmail() == null || request.getPassword() == null) {
//            throw new IllegalArgumentException("Username, email, and password cannot be null");
//        }
//
//        UserEntity childUser = new UserEntity();
//        childUser.setUsername(request.getUsername());
//        childUser.setEmail(request.getEmail());
//        childUser.setRole(Role.CHILD);
//        childUser.setPassword(request.getPassword());
//
//        UserEntity savedChildUser = userRepository.save(childUser);
//
//        ChildEntity child = new ChildEntity();
//                    child.setUser(savedChildUser);
//                    child.setParent(parent);
//                    child.setBalance(request.getInitialBalance());
//                    child.setDateOfBirth(request.getDateOfBirth());
//                    child.setEmail(request.getEmail());
//                    child.setUsername(request.getUsername());
//
//
//        ChildEntity savedChild = childRepository.save(child);
//
//        ChildResponse response = new ChildResponse( );
//        response.setId(child.getId());
//        response.setBalance(child.getBalance());
//        response.setDateOfBirth(child.getDateOfBirth());
//        response.setParentId(child.getParent().getId());
//        response.setUsername(childUser.getUsername());
//        response.setEmail(childUser.getEmail());
//
//
//        return response;
//    }

    public ChildResponse addChild(AddChildRequest request) {
        // Get the current authenticated user from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity parent = (UserEntity) authentication.getPrincipal();  // Assumes the principal is of type UserEntity

        // Ensure the authenticated user is a parent
        if (parent.getRole() != Role.PARENT) {
            throw new RuntimeException("Only parents can add children");
        }

        // Validate the request fields
        if (request.getUsername() == null || request.getEmail() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Username, email, and password cannot be null");
        }

        // Create the child user entity
        UserEntity childUser = new UserEntity();
        childUser.setUsername(request.getUsername());
        childUser.setEmail(request.getEmail());
        childUser.setRole(Role.CHILD);
        childUser.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save the child user entity
        UserEntity savedChildUser = userRepository.save(childUser);

        // Create the child entity and link it to the parent
        ChildEntity child = new ChildEntity();
        child.setUser(savedChildUser);
        child.setParent(parent);  // Use the authenticated parent
        child.setBalance(request.getInitialBalance());
        child.setDateOfBirth(request.getDateOfBirth());
        child.setEmail(request.getEmail());
        child.setUsername(request.getUsername());

        // Save the child entity
        ChildEntity savedChild = childRepository.save(child);

        // Prepare and return the response
        ChildResponse response = new ChildResponse();
        response.setId(savedChild.getId());
        response.setBalance(savedChild.getBalance());
        response.setDateOfBirth(savedChild.getDateOfBirth());
        response.setParentId(savedChild.getParent().getId());
        response.setUsername(savedChildUser.getUsername());
        response.setEmail(savedChildUser.getEmail());

        return response;
    }


    public Optional<ChildEntity> getChildEntityById(Long id){
        return childRepository.findById(id);
    }
}
