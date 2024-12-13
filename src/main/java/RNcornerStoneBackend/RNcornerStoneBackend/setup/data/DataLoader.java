package RNcornerStoneBackend.RNcornerStoneBackend.setup.data;

import RNcornerStoneBackend.RNcornerStoneBackend.setup.entity.QuizQuestionEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class DataLoader {



    public static List<QuizQuestionEntity> loadQuizQuestions(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load the file from the resources directory
        try (InputStream inputStream = DataLoader.class.getResourceAsStream("/predefinedData/"+fileName)) {

            // Check if the file is found in the classpath
            if (inputStream == null) {
                throw new RuntimeException("quiz_questions_bank.json file not found in the resources.");
            }

            // Map the JSON content to a List of QuizQuestionEntity objects
            return objectMapper.readValue(inputStream, new TypeReference<List<QuizQuestionEntity>>() {});
        } catch (Exception e) {
            return null;
        }
    }
}
