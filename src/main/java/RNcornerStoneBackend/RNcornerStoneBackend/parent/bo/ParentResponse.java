package RNcornerStoneBackend.RNcornerStoneBackend.parent.bo;

import java.util.List;

import RNcornerStoneBackend.RNcornerStoneBackend.child.bo.ChildResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.Role;

public class ParentResponse {
  private Long id;
  private String username;
  private String email;
  private Role role;
  private String avatarUrl;
  private List<ChildResponse> children;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public List<ChildResponse> getChildren() {
    return children;
  }

  public void setChildren(List<ChildResponse> children) {
    this.children = children;
  }
}