package RNcornerStoneBackend.RNcornerStoneBackend.controller;


import RNcornerStoneBackend.RNcornerStoneBackend.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.Setup.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class authController {

    private final UserService userService;

    public authController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>>  createUser(@RequestBody CreateUserRequest request){
        Boolean requestStatus = userService.CreateUserAccount(request);
        if (!requestStatus) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "message", "Account created."
            ));
        } else {// otherwise, the required missing field is highlighted to client
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Account creation failed."
            ));
        }

    }

    @PostMapping("/addQuizQuestion")
    public QuizQuestionEntity testController(@RequestBody QuizQuestionEntity quizQuestion) {
        return quizQuestion;
    }


}
