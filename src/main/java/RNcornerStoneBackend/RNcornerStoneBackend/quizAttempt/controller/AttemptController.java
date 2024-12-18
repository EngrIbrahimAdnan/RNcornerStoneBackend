package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.AttemptResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo.CreateAttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity.AttemptEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.service.AttemptService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/attempt")
public class AttemptController {
    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitAttempt(@Valid @RequestBody CreateAttemptEntity request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", "Validation failed",
                    "errors", errorMessages));
        }

        AttemptResponse response = attemptService.addAttempt(request);
        if (response.getMessage().startsWith("Only child")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<AttemptEntity>> getAttemptsByQuestionId(@PathVariable Long questionId) {
        List<AttemptEntity> attempts = attemptService.getAllAttemptByQuestionID(questionId);
        if (attempts == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(attempts);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttemptEntity>> getAllAttempts() {
        List<AttemptEntity> attempts = attemptService.getAttempts();
        return ResponseEntity.ok(attempts);
    }

    @GetMapping("/stats/success-rate/{questionId}")
    public ResponseEntity<Map<String, Object>> getSuccessRate(@PathVariable Long questionId) {
        double successRate = attemptService.getSuccessRate(questionId);
        return ResponseEntity.ok(Map.of(
                "questionId", questionId,
                "successRate", successRate));
    }

    @GetMapping("/stats/total-rewards")
    public ResponseEntity<Map<String, Object>> getTotalRewards() {
        double totalRewards = attemptService.getTotalRewardsEarned();
        return ResponseEntity.ok(Map.of(
                "totalRewards", totalRewards));
    }
}
