package org.example.giftlist.giftlist;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiftListRepository extends MongoRepository<GiftList, String> {

}