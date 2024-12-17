package RNcornerStoneBackend.RNcornerStoneBackend.parent.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RNcornerStoneBackend.RNcornerStoneBackend.parent.bo.ParentResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.parent.service.ParentService;

@RestController
@RequestMapping("/parents")
public class ParentController {
  private final ParentService parentService;

  public ParentController(ParentService parentService) {
    this.parentService = parentService;
  }

  @GetMapping("/me")
  public ResponseEntity<ParentResponse> getCurrentParentWithChildren() {
    return ResponseEntity.ok(parentService.getCurrentParentWithChildren());
  }

  @GetMapping
  public ResponseEntity<List<ParentResponse>> getAllParentsWithChildren() {
    return ResponseEntity.ok(parentService.getAllParentsWithChildren());
  }
}