package org.example.giftlist.giftList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giftlist")
public class GiftListController {

    private final GiftListService giftListService;

    public GiftListController(GiftListService giftListService) {
        this.giftListService = giftListService;
    }

    @GetMapping
    public ResponseEntity<List<GiftList>> getAllGiftList() {
        List<GiftList> giftLists = giftListService.getAllGiftList();
        if (giftLists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(giftListService.getAllGiftList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftList> getGiftListById(@PathVariable String id) {
        return ResponseEntity.ok(giftListService.getGiftListById(id));
    }
}
