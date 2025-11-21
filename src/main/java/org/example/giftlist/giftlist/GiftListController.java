package org.example.giftlist.giftlist;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/giftlist")
public class GiftListController {

    private final GiftListService giftListService;

    public GiftListController(GiftListService giftListService) {
        this.giftListService = giftListService;
    }

    @GetMapping
    public ResponseEntity<List<GiftList>> getAllGiftList() {
        return Optional.of(giftListService.getAllGiftList())
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftList> getGiftListById(@PathVariable String id) {
        return giftListService.getGiftListById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GiftList> createGiftList(@Valid @RequestBody GiftList giftList) {
        GiftList created = giftListService.createGiftList(giftList);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiftList> updateGiftList(
            @PathVariable String id,
            @RequestBody GiftList giftList
    ) {
        GiftList updated = giftListService.updateGiftList(id, giftList);
        return ResponseEntity.ok(updated);
    }
}
