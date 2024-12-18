package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.service;

import RNcornerStoneBackend.RNcornerStoneBackend.Auth.services.AuthenticationService;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.CreateAttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.RequestAttemptByID;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.repository.AttemptRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.service.QuizQuestionService;
import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.LoginUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.Role;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Date;

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

        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        if (user.getRole() == Role.CHILD) {
            AttemptEntity attemptEntity = new AttemptEntity();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            Optional<QuizQuestionEntity> quizQuestionEntity = quizQuestionService.getQuestionById(request.getQuestion_id());

            if (quizQuestionEntity.isEmpty()) {
                return "Question not found. Please provide an existing Question ID";
            }

            attemptEntity.setChildUserEntity(user);
            attemptEntity.setQuizQuestionEntity(quizQuestionEntity.get());
            attemptEntity.setAnswer(request.getAnswer());
            attemptEntity.setDate(date);
            attemptRepository.save(attemptEntity);
            return null;
        } else {
            return "Only child User can add attempt, Not Parent.";
        }
    }

    public List<AttemptEntity> getAllAttemptByID() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        if (user.getRole() == Role.CHILD) {
            return attemptRepository.findAllByChildUserEntity(user);
        } else {
            return null;
        }
    }

    public AttemptEntity getAttemptById(RequestAttemptByID request) {
        return attemptRepository.findById(request.getQuestion_id()).get();
    }

}