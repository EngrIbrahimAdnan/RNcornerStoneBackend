package RNcornerStoneBackend.RNcornerStoneBackend.service;

import RNcornerStoneBackend.RNcornerStoneBackend.bo.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Boolean CreateUserAccount(CreateUserRequest request);
}
