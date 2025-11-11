package org.example.giftlist.giftList;

import org.example.giftlist.gift.Gift;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@ActiveProfiles("test")
public class GiftListRepositoryTests {

    @Autowired
    private GiftListRepository giftListRepository;

    @BeforeEach
    void setUp() {
        giftListRepository.deleteAll();
    }

    @Test
    void shouldRetrieveListGift_whenIdIsGiven() {
        // Arrange
        Gift gift = new Gift().gift()
                .name("truc")
                .price(12)
                .build();

        GiftList list = new GiftList().giftList()
                .name("Anniversaire")
                .gifts(List.of(gift))
                .build();

        // Act
        GiftList saved = giftListRepository.findGiftListById(list.getId());

        // Assert
        assertThat(giftListRepository.count()).isEqualTo(1);
    }
}
