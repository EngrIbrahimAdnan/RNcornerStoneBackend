package RNcornerStoneBackend.RNcornerStoneBackend.user.entity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Entity.ChoreEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.child.entity.ChildEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 100, nullable = true)
    private String email;

    @Column(unique = true, length = 100, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @Column(nullable = true)
    private String avatarUrl;

    @OneToMany(mappedBy = "parent", orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "parent" })
    private List<ChildEntity> children;

    @OneToMany(mappedBy = "parent", orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "parent" })
    private List<ChoreEntity> chores;

    public List<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(List<ChildEntity> children) {
        this.children = children;
    }

    public List<ChoreEntity> getChores() {
        return chores;
    }

    public void setChores(List<ChoreEntity> chores) {
        this.chores = chores;
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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true; // Check if the references are the same
        if (o == null || getClass() != o.getClass())
            return false; // Ensure type compatibility
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && // Compare IDs
                Objects.equals(email, that.email) && // Compare email
                Objects.equals(username, that.username) && // Compare username
                Objects.equals(password, that.password) && // Compare password
                role == that.role; // Compare role (enums are compared using `==`)
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, role); // Generate a hash code using all fields
    }

}