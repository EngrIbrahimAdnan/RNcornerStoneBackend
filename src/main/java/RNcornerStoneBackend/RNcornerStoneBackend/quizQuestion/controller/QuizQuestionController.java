package RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.controller;


import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.CreateAttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo.CreateQuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo.RequestQuestionById;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.service.QuizQuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/question")
public class QuizQuestionController {

    private final QuizQuestionService quizQuestionService;

    public QuizQuestionController(QuizQuestionService quizQuestionService) {
        this.quizQuestionService = quizQuestionService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addQuizQuestion(@Valid @RequestBody CreateQuizQuestionEntity request, BindingResult bindingResult) {

        // If there are validation errors, return them
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", "Validation failed",
                    "errors", errorMessages
            ));
        }

        String requestStatus = quizQuestionService.addQuestion(request);

        if (requestStatus == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "message", "The Quiz Question has been added to database."
            ));
        } else {// otherwise, the required missing field is highlighted to client
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", requestStatus
            ));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<QuizQuestionEntity> getQuizQuestion(@Valid @RequestBody RequestQuestionById request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Optional<QuizQuestionEntity> questionEntity = quizQuestionService.getQuestionById(request.getQuestion_id());

        if (questionEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(questionEntity.get());
        } else {// otherwise, the required missing field is highlighted to client
            return ResponseEntity.badRequest().body(null);
        }

    }


    @GetMapping("/getall")
    public ResponseEntity<List<QuizQuestionEntity>> getAllQuestions() {

        List<QuizQuestionEntity> questionEntity = quizQuestionService.getAll();

        if (questionEntity != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(questionEntity);
        } else {// otherwise, the required missing field is highlighted to client
            return ResponseEntity.badRequest().body(null);
        }

    }
}
