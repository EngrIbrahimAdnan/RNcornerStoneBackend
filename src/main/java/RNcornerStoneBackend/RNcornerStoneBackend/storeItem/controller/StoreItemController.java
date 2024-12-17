package RNcornerStoneBackend.RNcornerStoneBackend.storeItem.controller;

import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.bo.CreateStoreItemEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.storeItem.service.ItemStoreService;
import RNcornerStoneBackend.RNcornerStoneBackend.user.bo.CreateUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/store")
@RestController
public class StoreItemController {
//    private final ItemStoreService itemStoreService;
//
//    public StoreItemController(ItemStoreService itemStoreService) {
//        this.itemStoreService = itemStoreService;
//    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addStoreItem(@Valid @RequestBody CreateStoreItemEntity request, BindingResult bindingResult) {

        // If there are validation errors, return them
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", "Validation failed",
                    "errors", errorMessages
            ));
        }

        String requestStatus = null;
        if (requestStatus == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "message", "Yay."
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", "Error creating Account"
            ));
        }
    }


}
