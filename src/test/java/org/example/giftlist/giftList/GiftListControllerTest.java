package org.example.giftlist.giftList;

import org.example.giftlist.gift.Gift;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GiftListController.class)
public class GiftListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GiftListService giftListService;

    @Test
    public void shouldGetAllGiftLists() throws Exception {

        // Arrange
        Gift gift = new Gift().gift()
                .name("Gift 1")
                .price(33)
                .build();

        Gift gift2 = new Gift().gift()
                .name("Gift 2")
                .price(21)
                .build();

        List<GiftList> giftLists = List.of(new GiftList().giftList().gifts(List.of(gift, gift2)).name("Noel").build(),
                new GiftList().giftList().gifts(List.of(gift)).name("Anniv").build());

        when(giftListService.getAllGiftList()).thenReturn(giftLists);

        // Act && Assert
        mockMvc.perform(get("/giftlist")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(giftLists.size()))
                .andExpect(jsonPath("$[0].name").value("Noel"))
                .andExpect(jsonPath("$[0].gifts[0].name").value("Gift 1"))
                .andExpect(jsonPath("$[0].gifts[0].price").value(33))
                .andExpect(jsonPath("$[0].gifts[1].price").value(21))
                .andExpect(jsonPath("$[1].name").value("Anniv"))
                .andExpect(jsonPath("$[1].gifts[0].name").value("Gift 1"))
                .andExpect(jsonPath("$[1].gifts[0].price").value(33));
    }

    @Test
    void shouldReturnGiftListById_whenIdExists() throws Exception {
        // Arrange
        String giftListId = "6914b6d4efab04099f43878f";

        Gift gift1 = new Gift().gift()
                .name("Gift 1")
                .price(33)
                .build();

        Gift gift2 = new Gift().gift()
                .name("Gift 2")
                .price(21)
                .build();

        GiftList giftList = new GiftList().giftList()
                .id(giftListId)
                .name("Noel")
                .gifts(List.of(gift1, gift2))
                .build();

        when(giftListService.getGiftListById(giftListId)).thenReturn(giftList);

        // Act & Assert
        mockMvc.perform(get("/giftlist/{id}", giftListId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(giftListId))
                .andExpect(jsonPath("$.name").value("Noel"))
                .andExpect(jsonPath("$.gifts[0].name").value("Gift 1"))
                .andExpect(jsonPath("$.gifts[0].price").value(33))
                .andExpect(jsonPath("$.gifts[1].name").value("Gift 2"))
                .andExpect(jsonPath("$.gifts[1].price").value(21));
    }
}
