package RNcornerStoneBackend.RNcornerStoneBackend.User.service;

import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Boolean CreateUserAccount(CreateUserRequest request);
    String CreateAuthUserAccount(UserEntity request);
    Optional<UserEntity> getUserById(Long id);
    Optional<UserEntity> getUserByUsername(String username);
    Optional<UserDetails> getUserByEmail(String email);

}

