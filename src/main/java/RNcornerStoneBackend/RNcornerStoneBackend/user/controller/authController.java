package RNcornerStoneBackend.RNcornerStoneBackend.user.controller;


import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.service.UserService;
import org.springframework.web.bind.annotation.*;

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
