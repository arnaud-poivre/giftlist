package org.example.giftlist.giftlist;

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
}
