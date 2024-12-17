package RNcornerStoneBackend.RNcornerStoneBackend.Auth.services;

import RNcornerStoneBackend.RNcornerStoneBackend.setup.service.SetupService;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo.CreateQuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.service.QuizQuestionService;
import RNcornerStoneBackend.RNcornerStoneBackend.Auth.data.DataLoader;
import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.LoginUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.Role;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private JwtService jwtService;

    private final UserRepository userRepository;
    private final SetupService setupService;

    private final QuizQuestionService quizQuestionService;

    public AuthenticationService(
            UserService userService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            SetupService setupService, JwtService jwtService
            QuizQuestionService quizQuestionService
    ) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.setupService = setupService;
        this.jwtService = jwtService;
    }


    public CreateUserRequest signup(CreateUserRequest input) {
        this.quizQuestionService = quizQuestionService;
    }


    public String signUp(CreateUserRequest input) {


        CreateUserRequest newRequest = new CreateUserRequest();
        newRequest.setUsername(input.getUsername());
        newRequest.setPassword(passwordEncoder.encode(input.getPassword()));
        newRequest.setEmail(input.getEmail());
        newRequest.setRole(Role.PARENT);
        newRequest.setAvatarUrl(input.getAvatarUrl());

        // Save the user to the database
        UserEntity savedUser = userRepository.save(newRequest.toUserEntity());

        // Generate JWT token
        String jwtToken = jwtService.generateToken(savedUser);
        newRequest.setToken(jwtToken);
        return userService.CreateUserAccount(newRequest);
        return newRequest;
    }


    public UserEntity authenticate(LoginUserRequest input) {

        System.out.println("Username: " + input.getUsername());
        System.out.println("Password: " + input.getPassword());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        UserEntity user = userService.getUserByUsername(input.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return user;
    }

    public <T> String addEntitiesToDatabaseFromFile(
            String file,
            TypeReference<List<T>> typeReference
    ) {

        // Load entities from the JSON file
        List<T> entities = DataLoader.loadEntities(file, typeReference);

        // Returns here only when the file is not found
        if (entities == null) {
            return "Unable to find the file. Check the path to file '" + file + "'";
        }

        try {
            // Loop through and save each entity
            for (T entity : entities) {
                switch (file) {
                    case "quiz_questions_bank.json":
                        CreateQuizQuestionEntity requestQuestionCreate = new CreateQuizQuestionEntity();
                        BeanUtils.copyProperties(entity, requestQuestionCreate);

                        quizQuestionService.addQuestion(requestQuestionCreate);
                        break;

                    case "users.json":
                        CreateUserRequest requestUserCreate = new CreateUserRequest();
                        BeanUtils.copyProperties(entity, requestUserCreate);
                        String request = signUp(requestUserCreate);

                        break;

                    default:
                        return "No file found.";
                }
            }
            return null;

        } catch (Exception e) {
            // Return an error string if there is an issue saving to the database
            return "Unable to add data to database. Ensure they are in the expected JSON structure";
        }
    }
}
