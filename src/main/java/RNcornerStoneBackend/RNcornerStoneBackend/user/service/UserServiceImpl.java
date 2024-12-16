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
    public Boolean CreateUserAccount(CreateUserRequest request) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername().toLowerCase());
        userEntity.setPassword(request.getPassword());
        userEntity.setEmail(request.getEmail());

        userRepository.save(userEntity);
        return false;
    }

    @Override
    public String CreateAuthUserAccount(UserEntity request) {
        try{
            userRepository.save(request);
        } catch (Exception e) {
            return "Error creating an account to database";
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
