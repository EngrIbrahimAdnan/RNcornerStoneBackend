package RNcornerStoneBackend.RNcornerStoneBackend.setup.data;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class DataLoader {

    public static <T> List<T> loadEntities(String fileName, TypeReference<List<T>> typeReference) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load the file from the resources directory
        try (InputStream inputStream = DataLoader.class.getResourceAsStream("/predefinedData/" + fileName)) {

            // Map the JSON content to a List of the specified type
            return objectMapper.readValue(inputStream, typeReference);

        } catch (Exception e) {
            // Return null if there is an error (e.g., file not found or parsing error)
            return null;
        }
    }
}
