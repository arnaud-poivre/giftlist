package org.example.giftlist.giftList;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftListService {

    private final GiftListRepository giftListRepository;

    public GiftListService(GiftListRepository giftListRepository) {
        this.giftListRepository = giftListRepository;
    }

    public List<GiftList> getAllGiftList() {
        return giftListRepository.findAll();
    }


}
