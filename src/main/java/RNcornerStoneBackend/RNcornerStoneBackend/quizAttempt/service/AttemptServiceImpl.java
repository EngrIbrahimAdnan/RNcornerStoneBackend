package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.service;

import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.CreateAttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.repository.AttemptRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.setup.service.SetupService;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.repository.UserRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttemptServiceImpl implements AttemptService {
    private final AttemptRepository attemptRepository;
    private final UserService userService;

    public AttemptServiceImpl(AttemptRepository attemptRepository, UserService userService) {
        this.attemptRepository = attemptRepository;
        this.userService = userService;

    }

    @Override
    public String addAttempt(CreateAttemptEntity request){

        if (doesUserExistInDatabase(request)){
            return "User in the request does not match any User in the database";
        }

        AttemptEntity attemptEntity = new AttemptEntity();
        attemptEntity.setChildUserEntity(request.getChildUserEntity());
        attemptEntity.setQuizQuestionEntity(request.getQuizQuestionEntity());
        attemptEntity.setAnswer(request.getAnswer());
        attemptEntity.setDate(request.getDate());

        attemptRepository.save(attemptEntity);
        return null;
    }

    public Boolean doesUserExistInDatabase(CreateAttemptEntity request){
        Optional<UserEntity> childUserFromDB = userService.getUserById(request.getChildUserEntity().getId());

        // Check that Child User entity passed actually corresponds to an existing User entity in the database
        if (!childUserFromDB.isPresent()) {
            return true;
        }

        UserEntity dbUser = childUserFromDB.get();
        UserEntity requestUser = request.getChildUserEntity();

        // Compare the two UserEntity objects using equals()
        if (!dbUser.equals(requestUser)) {
            return true;
        }
        return false;
    }

//    public Boolean doesQuestionExistInDatabase(CreateAttemptEntity request){
//        Optional<QuizQuestionEntity> QuestionEntityFromDB = userService.getUserById(request.getChildUserEntity().getId());
//
//        // Check that Child User entity passed actually corresponds to an existing User entity in the database
//        if (!childUserFromDB.isPresent()) {
//            return true;
//        }
//
//        UserEntity dbUser = childUserFromDB.get();
//        UserEntity requestUser = request.getChildUserEntity();
//
//        // Compare the two UserEntity objects using equals()
//        if (!dbUser.equals(requestUser)) {
//            return true;
//        }
//        return false;
//    }
}
