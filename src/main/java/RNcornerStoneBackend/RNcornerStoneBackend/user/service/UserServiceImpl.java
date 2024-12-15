package RNcornerStoneBackend.RNcornerStoneBackend.user.service;

import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.user.repository.UserRepository;
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

        if (request.getUsername()==null || request.getPassword()==null){
            return true;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername().toLowerCase());
        userEntity.setPassword(request.getPassword());

        userRepository.save(userEntity);
        return false;
    }

    @Override
    public Optional<UserEntity> getUserById(Long id){
        return userRepository.findById(id);
    }

}
