package RNcornerStoneBackend.RNcornerStoneBackend.child.controller;

import RNcornerStoneBackend.RNcornerStoneBackend.child.ChildService.ChildService;
import RNcornerStoneBackend.RNcornerStoneBackend.child.bo.AddChildRequest;
import RNcornerStoneBackend.RNcornerStoneBackend.child.bo.ChildResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/children")
public class ChildController {
    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping("/parent/{parentId}")
    public ResponseEntity<ChildResponse> addChild(@PathVariable Long parentId, @RequestBody AddChildRequest request) {
        return ResponseEntity.ok(childService.addChild(parentId, request));
    }
}
