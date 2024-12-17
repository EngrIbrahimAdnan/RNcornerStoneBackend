package RNcornerStoneBackend.RNcornerStoneBackend.user.bo;

import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.Role;
public class CreateUserRequest {

    @NotNull(message = "The 'username' field is required and it's missing")
    private String username;

    @NotNull(message = "The 'password' field is required and it's missing")
    private String password;

    @NotNull(message = "The 'email' field is required and it's missing")
    private String email;

    private Role role;

    private String avatarUrl;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

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

    public UserEntity toUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(this.username);
        userEntity.setPassword(this.password); // Ensure password is already encoded
        userEntity.setEmail(this.email);
        userEntity.setRole(this.role);
        userEntity.setAvatarUrl(this.avatarUrl);
        return userEntity;
    }
}
