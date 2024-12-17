package RNcornerStoneBackend.RNcornerStoneBackend.child.Repository;

import RNcornerStoneBackend.RNcornerStoneBackend.child.entity.ChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<ChildEntity, Long> {
    Optional<ChildEntity> findById(Long id);
}
