package RNcornerStoneBackend.RNcornerStoneBackend.child.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RNcornerStoneBackend.RNcornerStoneBackend.Chore.Service.ChoreService;
import RNcornerStoneBackend.RNcornerStoneBackend.Chore.bo.ChoreResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.child.ChildService.ChildService;
import RNcornerStoneBackend.RNcornerStoneBackend.child.bo.AddChildRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.child.bo.ChildResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.StoreItemResponse;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.service.ItemStoreService;

@RestController
@RequestMapping("/children")
public class ChildController {
    private final ChildService childService;
    private final ChoreService choreService;
    private final ItemStoreService itemStoreService;

    public ChildController(ChildService childService, ChoreService choreService, ItemStoreService itemStoreService) {
        this.childService = childService;
        this.choreService = choreService;
        this.itemStoreService = itemStoreService;
    }

    @GetMapping("/me")
    public ResponseEntity<ChildResponse> getCurrentChild() {
        return ResponseEntity.ok(childService.getCurrentChild());
    }

    @GetMapping("/me/chores")
    public ResponseEntity<List<ChoreResponse>> getCurrentChildChores() {
        return ResponseEntity.ok(choreService.getChoresForCurrentChild());
    }

    @GetMapping("/me/store-items")
    public ResponseEntity<List<StoreItemResponse>> getCurrentChildStoreItems() {
        return ResponseEntity.ok(itemStoreService.getCurrentChildStoreItems());
    }

    @PostMapping("/parent")
    public ResponseEntity<ChildResponse> addChild(@RequestBody AddChildRequest request) {
        return ResponseEntity.ok(childService.addChild(request));
    }
}
