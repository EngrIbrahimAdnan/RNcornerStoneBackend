package RNcornerStoneBackend.RNcornerStoneBackend.User.bo;

import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.Role;

public class UserResponse {
    private String email;
    private String username;
    private Long id;
    private Role role;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
