package RNcornerStoneBackend.RNcornerStoneBackend.parent.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Service.ChoreService;
import RNcornerStoneBackend.RNcornerStoneBackend.Chore.bo.ChoreResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.parent.bo.ParentResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.parent.service.ParentService;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.StoreItemResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.service.ItemStoreService;

@RestController
@RequestMapping("/parents")
public class ParentController {
  private final ParentService parentService;
  private final ChoreService choreService;
  private final ItemStoreService itemStoreService;

  public ParentController(ParentService parentService, ChoreService choreService, ItemStoreService itemStoreService) {
    this.parentService = parentService;
    this.choreService = choreService;
    this.itemStoreService = itemStoreService;
  }

  @GetMapping("/me")
  public ResponseEntity<ParentResponse> getCurrentParentWithChildren() {
    return ResponseEntity.ok(parentService.getCurrentParentWithChildren());
  }

  @GetMapping
  public ResponseEntity<List<ParentResponse>> getAllParentsWithChildren() {
    return ResponseEntity.ok(parentService.getAllParentsWithChildren());
  }

  @GetMapping("/children/{childId}/chores")
  public ResponseEntity<List<ChoreResponse>> getChildChores(@PathVariable Long childId) {
    return ResponseEntity.ok(choreService.getChoresForParentChild(childId));
  }

  @GetMapping("/children/{childId}/store-items")
  public ResponseEntity<List<StoreItemResponse>> getChildStoreItems(@PathVariable Long childId) {
    return ResponseEntity.ok(itemStoreService.getStoreItemsForParentChild(childId));
  }
}