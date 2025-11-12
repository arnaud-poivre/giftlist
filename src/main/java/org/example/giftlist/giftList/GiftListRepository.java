package org.example.giftlist.giftList;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiftListRepository extends MongoRepository<GiftList, String> {

    GiftList findGiftListById(String Id);
}