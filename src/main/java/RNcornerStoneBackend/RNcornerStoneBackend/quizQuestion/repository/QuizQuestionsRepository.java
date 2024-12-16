package RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.repository;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizQuestionsRepository  extends JpaRepository<QuizQuestionEntity, Long>{
    Optional<QuizQuestionEntity> findById(Long id);

}