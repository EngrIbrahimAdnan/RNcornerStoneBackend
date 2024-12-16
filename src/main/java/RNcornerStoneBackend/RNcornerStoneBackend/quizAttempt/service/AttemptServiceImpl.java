package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.service;

import RNcornerStoneBackend.RNcornerStoneBackend.Auth.services.AuthenticationService;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.CreateAttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.repository.AttemptRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.service.QuizQuestionService;
import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.LoginUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class AttemptServiceImpl implements AttemptService {
    private final AttemptRepository attemptRepository;
    private final UserService userService;
    private final QuizQuestionService quizQuestionService;
    private final AuthenticationService authenticationService;

    public AttemptServiceImpl(AttemptRepository attemptRepository,
                              UserService userService,
                              QuizQuestionService quizQuestionService,
                              AuthenticationService authenticationService) {
        this.attemptRepository = attemptRepository;
        this.userService = userService;
        this.quizQuestionService = quizQuestionService;
        this.authenticationService = authenticationService;

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
//            LoginUserRequest loginRequest = new LoginUserRequest();
//            loginRequest.setUsername(request.getChildUserEntity().getUsername());
//            loginRequest.setPassword(request.getChildUserEntity().getPassword());
//
//          UserEntity entity = authenticationService.authenticate(loginRequest);
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