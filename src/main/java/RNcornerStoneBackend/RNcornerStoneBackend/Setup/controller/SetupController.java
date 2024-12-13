package RNcornerStoneBackend.RNcornerStoneBackend.Setup.controller;

import RNcornerStoneBackend.RNcornerStoneBackend.Setup.service.SetupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/setup")
public class SetupController {
    private final SetupService setupService;

    public SetupController(SetupService setupService) {
        this.setupService = setupService;
    }

    @PostMapping("/loadBankQuizQuestions")
    public ResponseEntity<Map<String, Object>> testController() {

        String requestStatus = setupService.addQuizQuestionsToRepository();

        if (requestStatus==null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "message", "All Quiz Questions have been added to database."
            ));
        } else {// otherwise, the required missing field is highlighted to client
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Failed adding all Quiz Questions to database."
            ));
        }
    }

}
