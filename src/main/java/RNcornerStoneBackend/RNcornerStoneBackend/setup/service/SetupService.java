package RNcornerStoneBackend.RNcornerStoneBackend.setup.service;

import org.springframework.stereotype.Service;

@Service
public interface SetupService {
    String addQuestionsToDatabaseFromFile(String file);
}
