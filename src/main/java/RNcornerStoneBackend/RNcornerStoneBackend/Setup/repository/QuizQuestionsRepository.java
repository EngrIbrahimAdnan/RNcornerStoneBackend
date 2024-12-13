package RNcornerStoneBackend.RNcornerStoneBackend.Setup.repository;

import RNcornerStoneBackend.RNcornerStoneBackend.Setup.entity.QuizQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionsRepository  extends JpaRepository<QuizQuestionEntity, Long>{
}