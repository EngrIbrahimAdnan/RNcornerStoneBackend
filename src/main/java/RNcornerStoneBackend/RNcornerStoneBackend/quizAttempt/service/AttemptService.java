package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.AttemptResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.CreateAttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;

@Service
public interface AttemptService {
    AttemptResponse addAttempt(CreateAttemptEntity request);

    List<AttemptEntity> getAttempts();

    List<AttemptEntity> getAllAttemptByQuestionID(Long questionID);

    double getSuccessRate(Long questionId);

    double getTotalRewardsEarned();
}
