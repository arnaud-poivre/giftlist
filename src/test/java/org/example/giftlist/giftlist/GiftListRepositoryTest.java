package org.example.giftlist.giftlist;

import org.example.giftlist.config.AbstractMongoTest;
import org.example.giftlist.gift.Gift;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ActiveProfiles("test")
public class GiftListRepositoryTest extends AbstractMongoTest {

    @Autowired
    private GiftListRepository giftListRepository;

    @BeforeEach
    void setUp() {
        giftListRepository.deleteAll();
    }

    @Test
    void shouldRetrieveGiftList_whenIdIsGiven() {
        // Arrange
        Gift gift = new Gift().gift()
                .name("truc")
                .price(12)
                .build();

        GiftList list = new GiftList().giftList()
                .name("Anniversaire")
                .gifts(List.of(gift))
                .build();

        GiftList savedList = giftListRepository.save(list);

        // Act
        Optional<GiftList> foundList = giftListRepository.findGiftListById(savedList.getId());

        // Assert
        assertThat(foundList).isNotNull();
        assertThat(foundList.get().getName()).isEqualTo("Anniversaire");
        assertThat(foundList.get().getGifts()).hasSize(1);
        assertThat(giftListRepository.count()).isEqualTo(1);
    }
}
