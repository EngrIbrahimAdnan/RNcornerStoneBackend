package RNcornerStoneBackend.RNcornerStoneBackend.Setup.data;

import RNcornerStoneBackend.RNcornerStoneBackend.Setup.entity.QuizQuestionEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class DataLoader {

    public static List<QuizQuestionEntity> loadQuizQuestions() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Load the file from the resources directory
        try (InputStream inputStream = DataLoader.class.getResourceAsStream("/predefinedData/quiz_questions_bank.json")) {
            // Check if the file is found in the classpath
            if (inputStream == null) {
                throw new RuntimeException("quiz_questions_bank.json file not found in the resources.");
            }

            // Map the JSON content to a List of QuizQuestionEntity objects
            return objectMapper.readValue(inputStream, new TypeReference<List<QuizQuestionEntity>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load quiz questions data", e);
        }
    }
}
