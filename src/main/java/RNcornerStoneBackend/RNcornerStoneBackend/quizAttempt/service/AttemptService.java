package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.service;

import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.CreateAttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.RequestAttemptByID;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttemptService {
    String addAttempt(CreateAttemptEntity request);

    List<AttemptEntity> getAllAttemptByID();
    AttemptEntity getAttemptById(RequestAttemptByID request);

}
