package RNcornerStoneBackend.RNcornerStoneBackend.repository;

import RNcornerStoneBackend.RNcornerStoneBackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
//    UserEntity findByEmail(String email);
//    Optional<UserEntity> findByName(String username);
}
