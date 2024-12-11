package RNcornerStoneBackend.RNcornerStoneBackend.Auth.services;

import RNcornerStoneBackend.RNcornerStoneBackend.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.bo.LoginUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity signup(CreateUserRequest input) {
        UserEntity userEntity = new UserEntity();
                userEntity.setEmail(input.getEmail());
                userEntity.setUsername(input.getUsername());
                userEntity.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(userEntity);
    }

    public UserEntity authenticate(LoginUserRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}
