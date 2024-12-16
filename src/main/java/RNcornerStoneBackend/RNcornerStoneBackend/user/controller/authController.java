package RNcornerStoneBackend.RNcornerStoneBackend.User.controller;


import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.User.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ss")
public class authController {

    private final UserService userService;

    public authController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/addQuizQuestion")
    public QuizQuestionEntity testController(@RequestBody QuizQuestionEntity quizQuestion) {
        return quizQuestion;
    }


}
