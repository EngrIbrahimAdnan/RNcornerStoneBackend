package RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.service;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo.CreateQuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface QuizQuestionService {
    Optional<QuizQuestionEntity> getQuestionById(Long id);
    String addQuestion(CreateQuizQuestionEntity quizQuestionEntity);
}

