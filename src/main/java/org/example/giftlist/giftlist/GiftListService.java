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
        return giftListRepository.findById(id);
    }

    public GiftList createGiftList(GiftList giftList) {
        return giftListRepository.save(giftList);
    }

    public GiftList updateGiftList(String id, GiftList update) {
        GiftList existing = getGiftListAndUpdate(id, update);
        return giftListRepository.save(existing);
    }

    private GiftList getGiftListAndUpdate(String id, GiftList update) {
        GiftList existing = giftListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GiftList not found"));

        if (update.getName() != null) {
            existing.setName(update.getName());
        }

        if (update.getGifts() != null) {
            existing.setGifts(update.getGifts());
        }

        return existing;
    }
}
