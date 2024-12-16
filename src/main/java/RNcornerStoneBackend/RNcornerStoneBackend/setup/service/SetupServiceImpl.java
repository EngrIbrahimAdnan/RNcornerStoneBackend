package RNcornerStoneBackend.RNcornerStoneBackend.setup.service;

import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.user.service.UserService;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo.CreateQuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.service.QuizQuestionService;
import RNcornerStoneBackend.RNcornerStoneBackend.setup.data.DataLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetupServiceImpl implements SetupService {

    private final QuizQuestionService questionService;
    private final UserService userService;

    public SetupServiceImpl(QuizQuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;
    }

    @Override
    public <T> String addEntitiesToDatabaseFromFile(
            String file,
            TypeReference<List<T>> typeReference
    ) {

        // Load entities from the JSON file
        List<T> entities = DataLoader.loadEntities(file, typeReference);

        // Returns here only when the file is not found
        if (entities == null) {
            return "Unable to find the file. Check the path to file '" + file + "'";
        }

        try {
            // Loop through and save each entity
            for (T entity : entities) {
                switch (file) {
                    case "quiz_questions_bank.json":
                        CreateQuizQuestionEntity requestQuestionCreate = new CreateQuizQuestionEntity();
                        BeanUtils.copyProperties(entity, requestQuestionCreate);
                        questionService.addQuestion(requestQuestionCreate);
                        break;

                    case "users.json":
                        CreateUserRequest requestUserCreate = new CreateUserRequest();
                        BeanUtils.copyProperties(entity, requestUserCreate);
                        userService.CreateUserAccount(requestUserCreate);
                        break;

                    default:
                        return "No file found.";
                }
            }
            return null;

        } catch (Exception e) {
            // Return an error string if there is an issue saving to the database
            return "Unable to add data to database. Ensure they are in the expected JSON structure";
        }
    }
}