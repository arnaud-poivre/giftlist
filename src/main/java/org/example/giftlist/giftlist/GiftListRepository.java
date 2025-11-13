package org.example.giftlist.giftlist;

import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface GiftListRepository extends MongoRepository<GiftList, String> {

    Optional<GiftList> findGiftListById(String Id);
}