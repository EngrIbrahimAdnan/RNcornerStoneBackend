package RNcornerStoneBackend.RNcornerStoneBackend.User.service;

import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.UserResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
   // boolean CreateUserAccount(CreateUserRequest request);

    UserResponse CreateUserAccount(CreateUserRequest request);

    List<UserEntity> allUsers();
}
