package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import RNcornerStoneBackend.RNcornerStoneBackend.Auth.services.AuthenticationService;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.AttemptResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.CreateAttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.repository.AttemptRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.service.QuizQuestionService;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.Role;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.service.UserService;

@Service
public class AttemptServiceImpl implements AttemptService {
    private static final Logger logger = LoggerFactory.getLogger(AttemptServiceImpl.class);

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
    public AttemptResponse addAttempt(CreateAttemptEntity request) {
        try {
            // Get the current authenticated user
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication.getPrincipal() == null) {
                logger.error("No authenticated user found");
                return new AttemptResponse(false, 0, null, "Authentication required");
            }

            UserEntity user = (UserEntity) authentication.getPrincipal();
            if (user.getRole() != Role.CHILD) {
                logger.warn("Non-child user {} attempted to submit quiz", user.getId());
                return new AttemptResponse(false, 0, null, "Only child users can attempt quizzes");
            }

            Optional<QuizQuestionEntity> quizQuestionOpt = quizQuestionService
                    .getQuestionById(request.getQuestion_id());
            if (quizQuestionOpt.isEmpty()) {
                logger.warn("Question not found with ID: {}", request.getQuestion_id());
                return new AttemptResponse(false, 0, null, "Question not found");
            }

            QuizQuestionEntity question = quizQuestionOpt.get();

            // Check if user has already correctly answered this question
            if (hasCorrectAttempt(user, question)) {
                logger.info("User {} attempted already correctly answered question {}", user.getId(), question.getId());
                return new AttemptResponse(false, 0, null,
                        "You have already correctly answered this question. Try a different one!");
            }

            AttemptEntity attemptEntity = new AttemptEntity();
            attemptEntity.setChildUserEntity(user);
            attemptEntity.setQuizQuestionEntity(question);
            attemptEntity.setSelectedOption(request.getSelectedOption());
            attemptEntity.setDate(new Date());

            // Check if answer is correct
            boolean isCorrect = request.getSelectedOption().equals(question.getCorrectOption());
            attemptEntity.setCorrect(isCorrect);

            // Award points if correct
            double rewardAmount = isCorrect ? question.getRewardAmount() : 0;
            attemptEntity.setRewardEarned(rewardAmount);

            // Save the attempt
            attemptRepository.save(attemptEntity);
            logger.info("Quiz attempt saved for user {} on question {}, correct: {}", user.getId(), question.getId(),
                    isCorrect);

            String message = isCorrect ? String.format("Correct! You earned %.2f points!", rewardAmount)
                    : String.format("Incorrect. The correct answer was %s", question.getCorrectOption());

            return new AttemptResponse(
                    isCorrect,
                    rewardAmount,
                    question.getCorrectOption(),
                    message);

        } catch (Exception e) {
            logger.error("Error processing quiz attempt: {}", e.getMessage(), e);
            return new AttemptResponse(false, 0, null,
                    "An error occurred while processing your attempt. Please try again.");
        }
    }

    @Override
    public List<AttemptEntity> getAllAttemptByQuestionID(Long questionID) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication.getPrincipal() == null) {
                logger.error("No authenticated user found");
                return null;
            }

            UserEntity user = (UserEntity) authentication.getPrincipal();
            if (user.getRole() == Role.CHILD) {
                Optional<QuizQuestionEntity> questionEntity = quizQuestionService.getQuestionById(questionID);
                if (questionEntity.isPresent()) {
                    return attemptRepository.findAllByChildUserEntityAndQuizQuestionEntity(user, questionEntity.get());
                }
            }
            return null;
        } catch (Exception e) {
            logger.error("Error retrieving attempts for question {}: {}", questionID, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<AttemptEntity> getAttempts() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication.getPrincipal() == null) {
                logger.error("No authenticated user found");
                return null;
            }

            UserEntity user = (UserEntity) authentication.getPrincipal();
            return attemptRepository.findAllByChildUserEntity(user);
        } catch (Exception e) {
            logger.error("Error retrieving attempts: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public double getSuccessRate(Long questionId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication.getPrincipal() == null) {
                logger.error("No authenticated user found");
                return 0.0;
            }

            UserEntity user = (UserEntity) authentication.getPrincipal();
            Optional<QuizQuestionEntity> questionOpt = quizQuestionService.getQuestionById(questionId);
            if (questionOpt.isEmpty()) {
                logger.warn("Question not found with ID: {}", questionId);
                return 0.0;
            }

            List<AttemptEntity> attempts = attemptRepository.findAllByChildUserEntityAndQuizQuestionEntity(user,
                    questionOpt.get());
            if (attempts.isEmpty()) {
                return 0.0;
            }

            long correctAttempts = attempts.stream().filter(AttemptEntity::isCorrect).count();
            return (double) correctAttempts / attempts.size() * 100;
        } catch (Exception e) {
            logger.error("Error calculating success rate for question {}: {}", questionId, e.getMessage(), e);
            return 0.0;
        }
    }

    @Override
    public double getTotalRewardsEarned() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication.getPrincipal() == null) {
                logger.error("No authenticated user found");
                return 0.0;
            }

            UserEntity user = (UserEntity) authentication.getPrincipal();
            List<AttemptEntity> attempts = attemptRepository.findAllByChildUserEntity(user);
            return attempts.stream()
                    .mapToDouble(AttemptEntity::getRewardEarned)
                    .sum();
        } catch (Exception e) {
            logger.error("Error calculating total rewards: {}", e.getMessage(), e);
            return 0.0;
        }
    }

    private boolean hasCorrectAttempt(UserEntity user, QuizQuestionEntity question) {
        List<AttemptEntity> attempts = attemptRepository.findAllByChildUserEntityAndQuizQuestionEntity(user, question);
        return attempts.stream().anyMatch(AttemptEntity::isCorrect);
    }
}