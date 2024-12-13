package RNcornerStoneBackend.RNcornerStoneBackend.setup.repository;

import RNcornerStoneBackend.RNcornerStoneBackend.setup.entity.QuizQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionsRepository  extends JpaRepository<QuizQuestionEntity, Long>{
}