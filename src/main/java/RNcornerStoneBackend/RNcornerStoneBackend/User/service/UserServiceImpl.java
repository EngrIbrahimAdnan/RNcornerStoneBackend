package RNcornerStoneBackend.RNcornerStoneBackend.User.service;

import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.UserResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.Role;
import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.User.repository.UserRepository;
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
    public boolean CreateUserAccount(CreateUserRequest request) {
        if (request.getUsername()==null || request.getPassword()==null || request.getEmail()==null){
            return true;
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername().toLowerCase());
        userEntity.setEmail(request.getEmail().toLowerCase());
        userEntity.setRole(Role.valueOf(request.getRole().toString()));
        userEntity.setPassword(request.getPassword());


//        .save(account); // Save the account entity

        userRepository.save(userEntity);


        UserResponse response = new UserResponse();
        response.setId(userEntity.getId());
        response.setUsername(userEntity.getUsername());
        response.setEmail(userEntity.getEmail());
        response.setRole(userEntity.getRole());
        return response;
    }

    public List<UserEntity> allUsers() {
        List<UserEntity> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
