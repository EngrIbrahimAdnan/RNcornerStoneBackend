package RNcornerStoneBackend.RNcornerStoneBackend.user.service;

import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String CreateUserAccount(CreateUserRequest request) {

        if (!request.getRole().name().equals("CHILD") && !request.getRole().name().equals("PARENT")) {
            return "invalid Role input";
        }

        try{


            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(request.getEmail());
            userEntity.setUsername(request.getUsername());
            userEntity.setPassword(request.getPassword());
            userEntity.setRole(request.getRole());
            userRepository.save(userEntity);

        } catch (Exception e) {
            return "Error creating account with user request.";
        }
        return null;
    }


    @Override
    public Optional<UserEntity> getUserById(Long id){
        return userRepository.findById(id);
    }


    @Override
    public Optional<UserEntity> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserDetails> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
