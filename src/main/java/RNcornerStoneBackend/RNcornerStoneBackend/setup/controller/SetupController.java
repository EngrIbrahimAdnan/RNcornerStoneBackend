package RNcornerStoneBackend.RNcornerStoneBackend.setup.controller;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.setup.service.SetupService;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/setup")
public class SetupController {
    private final SetupService setupService;

    public SetupController(SetupService setupService) {
        this.setupService = setupService;
    }

    // endpoint to add all quiz questions defined from a json file to database for automation
    @PostMapping("/loadBankQuizQuestions")
    public ResponseEntity<Map<String, Object>> loadQuestions() {

        // returns null only if everything is successful, otherwise it returns the string stating the issue found
        String requestStatus = setupService.addEntitiesToDatabaseFromFile("quiz_questions_bank.json", new TypeReference<List<QuizQuestionEntity>>(){});

        if (requestStatus==null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "message", "All Quiz Questions have been added to database."
            ));
        } else {// otherwise, the required missing field is highlighted to client
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", requestStatus
            ));
        }
    }

    @PostMapping("/loadUsers")
    public ResponseEntity<Map<String, Object>> loadUsers() {

        // returns null only if everything is successful, otherwise it returns the string stating the issue found
        String requestStatus = setupService.addEntitiesToDatabaseFromFile("users.json", new TypeReference<List<UserEntity>>(){});

        if (requestStatus==null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "message", "All users have been added to database."
            ));
        } else {// otherwise, the required missing field is highlighted to client
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", requestStatus
            ));
        }
    }

}
