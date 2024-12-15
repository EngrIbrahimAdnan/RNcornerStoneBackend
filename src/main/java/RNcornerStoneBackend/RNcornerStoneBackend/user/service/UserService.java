package RNcornerStoneBackend.RNcornerStoneBackend.user.service;

import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Boolean CreateUserAccount(CreateUserRequest request);
    Optional<UserEntity> getUserById(Long id);


}
