package org.example.giftlist.giftList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok(giftListService.getAllGiftList());
    }


}
