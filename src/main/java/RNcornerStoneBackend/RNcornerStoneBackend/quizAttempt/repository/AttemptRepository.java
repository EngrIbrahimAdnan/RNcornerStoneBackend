package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.repository;

import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<AttemptEntity, Long> {
}
