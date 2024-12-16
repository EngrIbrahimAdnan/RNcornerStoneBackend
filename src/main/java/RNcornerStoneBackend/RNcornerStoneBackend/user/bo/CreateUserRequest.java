package RNcornerStoneBackend.RNcornerStoneBackend.user.bo;

import jakarta.validation.constraints.NotNull;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.Role;
public class CreateUserRequest {

    @NotNull(message = "The 'username' field is required and it's missing")
    private String username;

    @NotNull(message = "The 'password' field is required and it's missing")
    private String password;

    @NotNull(message = "The 'email' field is required and it's missing")
    private String email;

    @NotNull(message = "The 'role' field is required and it's missing")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
