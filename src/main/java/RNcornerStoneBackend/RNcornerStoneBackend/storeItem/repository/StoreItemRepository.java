package RNcornerStoneBackend.RNcornerStoneBackend.storeItem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.entity.StoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItemEntity, Long> {
    Optional<StoreItemEntity> findByChildUserEntity(UserEntity child);

    List<StoreItemEntity> findAllByChildUserEntity(UserEntity child);
}
