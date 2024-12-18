package RNcornerStoneBackend.RNcornerStoneBackend.parent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import RNcornerStoneBackend.RNcornerStoneBackend.child.bo.ChildResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.child.entity.ChildEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.parent.bo.ParentResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.Role;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.repository.UserRepository;

@Service
public class ParentService {
  private final UserRepository userRepository;

  public ParentService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public ParentResponse getCurrentParentWithChildren() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserEntity parent = (UserEntity) authentication.getPrincipal();

    if (parent.getRole() != Role.PARENT) {
      throw new RuntimeException("Only parents can access this endpoint");
    }

    return convertToParentResponse(parent);
  }

  public List<ParentResponse> getAllParentsWithChildren() {
    return userRepository.findByRole(Role.PARENT)
        .stream()
        .map(this::convertToParentResponse)
        .collect(Collectors.toList());
  }

  private ParentResponse convertToParentResponse(UserEntity parent) {
    ParentResponse response = new ParentResponse();
    response.setId(parent.getId());
    response.setUsername(parent.getUsername());
    response.setEmail(parent.getEmail());
    response.setRole(parent.getRole());
    response.setAvatarUrl(parent.getAvatarUrl());

    List<ChildResponse> childResponses = new ArrayList<>();
    if (parent.getChildren() != null) {
      childResponses = parent.getChildren().stream()
          .map(this::convertToChildResponse)
          .collect(Collectors.toList());
    }
    response.setChildren(childResponses);

    return response;
  }

  private ChildResponse convertToChildResponse(ChildEntity child) {
    ChildResponse response = new ChildResponse();
    response.setId(child.getId());
    response.setUsername(child.getUsername());
    response.setEmail(child.getEmail());
    response.setBalance(child.getBalance());
    response.setDateOfBirth(child.getDateOfBirth());
    response.setParentId(child.getParent().getId());
    return response;
  }
}