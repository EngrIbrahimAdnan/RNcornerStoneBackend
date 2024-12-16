package RNcornerStoneBackend.RNcornerStoneBackend.Auth.services;

import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.LoginUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.UserResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.Role;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.User.repository.UserRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.User.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;


    public AuthenticationService(
            UserService userService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

    }

    public String signup(CreateUserRequest input) {

        if (!input.getRole().name().equals("CHILD") && !input.getRole().name().equals("PARENT")) {
            return "invalid Role input";
        }


        try{
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(input.getEmail());
            userEntity.setUsername(input.getUsername());
            userEntity.setPassword(passwordEncoder.encode(input.getPassword()));
            userEntity.setRole(input.getRole());
            userService.CreateAuthUserAccount(userEntity);

        } catch (Exception e) {
            return "Error creating account with user request.";
        }

        return null;
    }

    public UserEntity authenticate(LoginUserRequest input) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        UserEntity user = userRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return user;
    }
}
