package org.example.giftlist.giftList;

import org.example.giftlist.gift.Gift;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class GiftListServiceTest {

    @MockitoBean
    private GiftListRepository giftListRepository;

    @Autowired
    private GiftListService giftListService;


    @Test
    void shouldGetAllTheGiftLists() {
        // Arrange
        List<GiftList> giftLists = List.of(
                new GiftList().giftList()
                        .gifts(List.of(new Gift().gift().name("Truc")
                                .price(23)
                                .build(),
                                new Gift().gift()
                                        .name("Machin")
                                        .price(23)
                                        .build()))
                        .name("Noel")
                        .build()
        );

        when(giftListRepository.findAll()).thenReturn(giftLists);

        // Act
        List<GiftList> result = giftListService.getAllGiftList();

        // Assert
        assertEquals(giftLists.size(), result.size());
    }

}