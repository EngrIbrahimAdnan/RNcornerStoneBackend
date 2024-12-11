package RNcornerStoneBackend.RNcornerStoneBackend.service;

import RNcornerStoneBackend.RNcornerStoneBackend.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    Boolean CreateUserAccount(CreateUserRequest request);

    List<UserEntity> allUsers();
}
