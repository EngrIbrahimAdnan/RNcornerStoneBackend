package RNcornerStoneBackend.RNcornerStoneBackend.setup.service;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SetupService {
    <T> String addEntitiesToDatabaseFromFile(
            String file,
            TypeReference<List<T>> typeReference
    );
}