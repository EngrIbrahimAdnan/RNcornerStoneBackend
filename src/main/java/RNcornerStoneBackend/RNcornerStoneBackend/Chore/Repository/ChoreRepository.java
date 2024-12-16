package RNcornerStoneBackend.RNcornerStoneBackend.Chore.Repository;

import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Entity.ChoreEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoreRepository extends JpaRepository<ChoreEntity, Long> {
    List<ChoreEntity> findByParentId(Long parentId);
    List<ChoreEntity> findByChildId(Long childId);
    List<ChoreEntity> findByChildIdAndStatus(Long childId, Status status);
    List<ChoreEntity> findByParentIdAndStatus(Long parentId, Status status);

}

