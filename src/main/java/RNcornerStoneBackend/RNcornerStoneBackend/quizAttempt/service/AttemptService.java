package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.service;

import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.CreateAttemptEntity;
import org.springframework.stereotype.Service;

@Service
public interface AttemptService {
    String addAttempt(CreateAttemptEntity request);
}
