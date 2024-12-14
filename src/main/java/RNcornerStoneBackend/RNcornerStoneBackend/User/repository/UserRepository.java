package RNcornerStoneBackend.RNcornerStoneBackend.User.repository;

import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
//    UserEntity findByEmail(String email);
//    Optional<UserEntity> CreateUserAccount(CreateUserRequest request);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByRole(Role role);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
