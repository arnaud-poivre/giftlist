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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        List<GiftList> giftLists = List.of(new GiftList().giftList().gifts(List.of(gift, gift2)).build(),
                new GiftList().giftList().gifts(List.of(gift)).build());

        when(giftListService.getAllGiftList()).thenReturn(giftLists);

        // Act && Assert
        mockMvc.perform(get("/giftlist")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(giftLists.size()));
    }


}