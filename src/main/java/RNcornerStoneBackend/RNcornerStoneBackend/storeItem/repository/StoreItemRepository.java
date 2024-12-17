package RNcornerStoneBackend.RNcornerStoneBackend.storeItem.repository;

import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.CreateStoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.entity.StoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItemEntity, Long> {
    Optional<StoreItemEntity> findByChildUserEntity(UserEntity child);
}


