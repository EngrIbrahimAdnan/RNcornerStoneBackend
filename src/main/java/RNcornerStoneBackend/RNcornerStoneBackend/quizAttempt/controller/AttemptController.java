package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.controller;

import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.CreateAttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.RequestAttemptsByQuestionID;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.service.AttemptService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attempt")
public class AttemptController {
    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> quizAttempt(@Valid @RequestBody CreateAttemptEntity request, BindingResult bindingResult) {

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

        String requestStatus = attemptService.addAttempt(request);

        if (requestStatus == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "message", "The Attempt has been added to database."
            ));
        } else {// otherwise, the required missing field is highlighted to client
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", requestStatus
            ));
        }
    }

    // Get all attempts for specified question id for current Child user
    @GetMapping("/question/id")
    public ResponseEntity<List<AttemptEntity>> getAllAttemptsForQuestionID(@Valid @RequestBody RequestAttemptsByQuestionID request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<AttemptEntity> list = attemptService.getAllAttemptByQuestionID(request.getQuestion_id());

        if (list != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        } else {// otherwise, the required missing field is highlighted to client
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttemptEntity>> getAllAttempts() {

        List<AttemptEntity> attempt = attemptService.getAttempts();

        if (attempt != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(attempt);
        } else {// otherwise, the required missing field is highlighted to client
            return ResponseEntity.badRequest().body(null);
        }

    }
}
