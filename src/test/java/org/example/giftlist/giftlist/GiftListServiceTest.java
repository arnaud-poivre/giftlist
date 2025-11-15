package org.example.giftlist.giftlist;

import org.example.giftlist.gift.Gift;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

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

    @Test
    void shouldGetGiftListById_whenCorrectIdIsGiven() {
        // Arrange
        GiftList giftLists = new GiftList().giftList()
                .id("6914b6d4efab04099f43878f")
                .gifts(List.of(new Gift().gift().name("Truc")
                                .price(23)
                                .build(),
                        new Gift().gift()
                                .name("Machin")
                                .price(23)
                                .build()))
                .name("Noel")
                .build();
        when(giftListRepository.findGiftListById("6914b6d4efab04099f43878f")).thenReturn(Optional.ofNullable(giftLists));

        // Act
        Optional<GiftList> result = giftListService.getGiftListById("6914b6d4efab04099f43878f");

        // Arrange
        assertNotNull(giftLists);
        assertEquals(result.get().getId(), giftLists.getId());
    }

    @Test
    void shouldCreateGiftList() {
        // Arrange
        GiftList newGiftList = new GiftList().giftList()
                .id("6914b6d4efab04099f43878f")
                .gifts(List.of(new Gift().gift().name("Truc")
                                .price(23)
                                .build(),
                        new Gift().gift()
                                .name("Machin")
                                .price(23)
                                .build()))
                .name("Noel")
                .build();

        when(giftListRepository.save(newGiftList)).thenReturn(newGiftList);

        // Act
        GiftList result = giftListService.createGiftList(newGiftList);

        // Arrange
        assertNotNull(result);
        assertEquals(newGiftList.getId(), result.getId());
        assertEquals(newGiftList.getName(), result.getName());
        assertEquals(newGiftList.getGifts().size(), result.getGifts().size());
    }

    @Test
    void shouldUpdateGiftList_whenCorrectIdIsGiven() {

        // Arrange
        GiftList giftListFromDB = new GiftList().giftList()
                .id("123")
                .name("Liste de Noel")
                .gifts(List.of(new Gift().gift().name("Truc").price(4).build(),
                        new Gift().gift().name("Machin").price(6).build()))
                .build();

        GiftList giftListUpdated = new GiftList().giftList()
                .id("123")
                .name("Liste de cadeau")
                .gifts(List.of(new Gift().gift().name("Nouveau").price(4).build()))
                .build();

        when(giftListRepository.findGiftListById("123")).thenReturn(Optional.ofNullable(giftListFromDB));
        when(giftListRepository.save(giftListFromDB)).thenReturn(giftListUpdated);

        // Act
        GiftList result = giftListService.updateGiftList(giftListFromDB);

        // Arrange
        assertNotNull(result);
        assertEquals(giftListUpdated.getId(), result.getId());
        assertEquals(giftListUpdated.getName(), result.getName());
        assertEquals(giftListUpdated.getGifts().size(), result.getGifts().size());
        assertEquals(giftListUpdated.getGifts().get(0).getName(), result.getGifts().get(0).getName());
        assertEquals(giftListUpdated.getGifts().get(0).getPrice(), result.getGifts().get(0).getPrice());
    }
}