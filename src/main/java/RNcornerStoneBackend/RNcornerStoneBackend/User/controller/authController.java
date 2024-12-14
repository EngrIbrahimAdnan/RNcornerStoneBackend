package RNcornerStoneBackend.RNcornerStoneBackend.User.controller;


import RNcornerStoneBackend.RNcornerStoneBackend.User.bo.UserResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.User.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class authController {

    private final UserServiceImpl userService;

    public authController(UserServiceImpl userService) {
        this.userService = userService;
    }

//    @PostMapping("/create")
//    public ResponseEntity<Map<String, Object>>  createUser(@RequestBody CreateUserRequest request){
//        UserResponse requestStatus = userService.CreateUserAccount(request);
//        if (requestStatus == null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
//                    "status", "success",
//                    "message", "Account created."
//            ));
//        } else {// otherwise, the required missing field is highlighted to client
//            return ResponseEntity.badRequest().body(Map.of(
//                    "status", "error",
//                    "message", "Account creation failed."
//            ));
//        }
//
//    }

//    @GetMapping("/yourname")
//    public String testController(@RequestBody String name) {
//        return "My name is " + name;
//    }


    @GetMapping("/me")
    public ResponseEntity<UserResponse> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity currentUser = (UserEntity) authentication.getPrincipal();

        UserResponse userResponse = new UserResponse();
        userResponse.setId(currentUser.getId());
        userResponse.setEmail(currentUser.getEmail());
        userResponse.setUsername(currentUser.getUsername());
        userResponse.setRole(currentUser.getRole());

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> allUsers() {
        List <UserEntity> users = userService.allUsers();
        List<UserResponse> userResponses = users.stream()
                .map(user -> {
                    UserResponse userResponse = new UserResponse();
                    userResponse.setId(user.getId());
                    userResponse.setEmail(user.getEmail());
                    userResponse.setUsername(user.getUsername());
                    userResponse.setRole(user.getRole());
                    return userResponse;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }
}
