package RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.service;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo.CreateQuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface QuestionService {
    Optional<QuizQuestionEntity> getQuestionById(Long id);
    void addQuestion(CreateQuizQuestionEntity quizQuestionEntity);
}

