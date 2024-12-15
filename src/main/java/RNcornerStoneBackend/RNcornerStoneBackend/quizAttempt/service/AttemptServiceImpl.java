package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.service;

import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.CreateAttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.repository.AttemptRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo.CreateQuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.service.QuizQuestionService;
import RNcornerStoneBackend.RNcornerStoneBackend.setup.service.SetupService;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.repository.UserRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.user.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttemptServiceImpl implements AttemptService {
    private final AttemptRepository attemptRepository;
    private final UserService userService;
    private final QuizQuestionService quizQuestionService;

    public AttemptServiceImpl(AttemptRepository attemptRepository, UserService userService, QuizQuestionService quizQuestionService) {
        this.attemptRepository = attemptRepository;
        this.userService = userService;
        this.quizQuestionService = quizQuestionService;

    }

    @Override
    public String addAttempt(CreateAttemptEntity request) {

        if (!doesUserExistInDatabase(request, "user")) {
            return "User in the request does not match any User in the database";
        }

        if (!doesUserExistInDatabase(request, "question")) {
            return "Question in the request does not match any Question in the database";
        }

        AttemptEntity attemptEntity = new AttemptEntity();
        attemptEntity.setChildUserEntity(request.getChildUserEntity());
        attemptEntity.setQuizQuestionEntity(request.getQuizQuestionEntity());
        attemptEntity.setAnswer(request.getAnswer());
        attemptEntity.setDate(request.getDate());

        attemptRepository.save(attemptEntity);
        return null;
    }


    public Boolean doesUserExistInDatabase(CreateAttemptEntity request, String type) {
        Optional<?> entity; // Declare the variable before the if/else block

        if (type.equals("user")) {
            entity = userService.getUserById(request.getChildUserEntity().getId());

            // Check if the entity exists in the database
            if (entity.isEmpty()) {

                return false;
            }

            if (!entity.get().equals(request.getChildUserEntity())) {

                return false;
            }

        } else if (type.equals("question")) {
            entity = quizQuestionService.getQuestionById(request.getQuizQuestionEntity().getId());

            // Check if the entity exists in the database
            if (entity.isEmpty() || !entity.get().equals(request.getQuizQuestionEntity())) {
                return false;
            }

        } else {
            return false; // Invalid type
        }

        return true;
    }
}