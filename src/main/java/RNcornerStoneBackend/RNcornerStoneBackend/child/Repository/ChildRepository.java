package RNcornerStoneBackend.RNcornerStoneBackend.child.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RNcornerStoneBackend.RNcornerStoneBackend.child.entity.ChildEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;

@Repository
public interface ChildRepository extends JpaRepository<ChildEntity, Long> {
    Optional<ChildEntity> findById(Long id);

    Optional<ChildEntity> findByUser(UserEntity user);
}
