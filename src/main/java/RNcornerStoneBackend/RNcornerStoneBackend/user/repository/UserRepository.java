package RNcornerStoneBackend.RNcornerStoneBackend.User.repository;

import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.LoginUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserDetails> findByEmail(String email);


}
