package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.repository;

import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttemptRepository extends JpaRepository<AttemptEntity, Long> {
    List<AttemptEntity> findAllByChildUserEntity(UserEntity user);
    Optional<AttemptEntity> findById(Long id);
}
