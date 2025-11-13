package org.example.giftlist.giftlist;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GiftListService {

    private final GiftListRepository giftListRepository;

    public GiftListService(GiftListRepository giftListRepository) {
        this.giftListRepository = giftListRepository;
    }

    public List<GiftList> getAllGiftList() {
        return giftListRepository.findAll();
    }

    public Optional<GiftList> getGiftListById(String id) {
        return giftListRepository.findGiftListById(id);
    }

    public GiftList createGiftList(GiftList giftList) {
        return giftListRepository.save(giftList);
    }

}
