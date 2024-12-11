package RNcornerStoneBackend.RNcornerStoneBackend.service;

import RNcornerStoneBackend.RNcornerStoneBackend.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean CreateUserAccount(CreateUserRequest request) {
        if (request.getUsername()==null || request.getPassword()==null || request.getEmail()==null){
            return true;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername().toLowerCase());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(request.getPassword());

//        .save(account); // Save the account entity

        userRepository.save(userEntity);
        return false;
    }

    public List<UserEntity> allUsers() {
        List<UserEntity> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
