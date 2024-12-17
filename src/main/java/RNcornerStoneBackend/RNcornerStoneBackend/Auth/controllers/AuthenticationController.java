package RNcornerStoneBackend.RNcornerStoneBackend.Auth.controllers;

import RNcornerStoneBackend.RNcornerStoneBackend.Auth.services.AuthenticationService;
import RNcornerStoneBackend.RNcornerStoneBackend.Auth.services.JwtService;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.LoginResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.LoginUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.UserResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;


    public AuthenticationController(JwtService jwtService,
                                    AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }



    @PostMapping("/signup")
    public ResponseEntity<CreateUserRequest> register(@RequestBody CreateUserRequest registerUserDto) {
        CreateUserRequest userResponse = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(userResponse);
    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserRequest loginUserDto) {


        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        Optional<UserEntity> userOptional = authenticationService.getUserById(id);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            UserResponse userResponse = new UserResponse(); // Create a DTO for user response
            // Populate userResponse with user data
            userResponse.setId(user.getId());
            userResponse.setUsername(user.getUsername());
            userResponse.setEmail(user.getEmail());
            userResponse.setRole(user.getRole());
            userResponse.setAvatarUrl(user.getAvatarUrl());
            // Set other fields as needed, but avoid sending sensitive information like passwords

            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    // Populates the database with quiz questions
//    @PostMapping("/setup/loadBankQuizQuestions")
//    public ResponseEntity<Map<String, Object>> loadQuestions() {
//
//        // returns null only if everything is successful, otherwise it returns the string stating the issue found
//        String requestStatus = authenticationService.addEntitiesToDatabaseFromFile("quiz_questions_bank.json", new TypeReference<List<QuizQuestionEntity>>() {
//        });
//
//        if (requestStatus == null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
//                    "status", "success",
//                    "message", "All Quiz Questions have been added to database."
//            ));
//        } else {// otherwise, the required missing field is highlighted to client
//            return ResponseEntity.badRequest().body(Map.of(
//                    "status", "error",
//                    "message", requestStatus
//            ));
//        }
//    }
//
//    // Populates the database with users questions
//    @PostMapping("/setup/loadUsers")
//    public ResponseEntity<Map<String, Object>> loadUsers() {
//
//        // returns null only if everything is successful, otherwise it returns the string stating the issue found
//        String requestStatus = authenticationService.addEntitiesToDatabaseFromFile("users.json", new TypeReference<List<UserEntity>>() {
//        });
//
//        if (requestStatus == null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
//                    "status", "success",
//                    "message", "All users have been added to database."
//            ));
//        } else {// otherwise, the required missing field is highlighted to client
//            return ResponseEntity.badRequest().body(Map.of(
//                    "status", "error",
//                    "message", requestStatus
//            ));
//        }
//    }


}

