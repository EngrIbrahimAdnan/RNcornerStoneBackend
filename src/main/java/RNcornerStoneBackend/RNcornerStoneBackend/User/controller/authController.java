package RNcornerStoneBackend.RNcornerStoneBackend.User.controller;


import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.CreateUserRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.User.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class authController {

    private final UserService userService;

    public authController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>>  createUser(@RequestBody CreateUserRequest request){
        Boolean requestStatus = userService.CreateUserAccount(request);
        if (!requestStatus) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "message", "Account created."
            ));
        } else {// otherwise, the required missing field is highlighted to client
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Account creation failed."
            ));
        }

    }

    @GetMapping("/yourname")
    public String testController(@RequestBody String name) {
        return "My name is " + name;
    }


    @GetMapping("/me")
    public ResponseEntity<UserEntity> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity currentUser = (UserEntity) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> allUsers() {
        List <UserEntity> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}
