package org.example.giftlist.giftlist;

import org.example.giftlist.gift.Gift;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GiftListControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

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
    void shouldReturn404_whenNoGiftListFound() throws Exception {
        // Arrange
        when(giftListService.getAllGiftList()).thenReturn(Collections.emptyList());

        // Act && Assert
        mockMvc.perform(get("/giftlist")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
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

        when(giftListService.getGiftListById(giftListId)).thenReturn(Optional.ofNullable(giftList));

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

    @Test
    void shouldReturn404_whenIdDoesNotExist() throws Exception {
        when(giftListService.getGiftListById("unknown")).thenReturn(Optional.empty());

        mockMvc.perform(get("/giftlist/unknown")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateGiftList() throws Exception {
        // Arrange
        GiftList newGiftList = new GiftList().giftList()
                .id("6914b6d4efab04099f43878f")
                .gifts(List.of(
                        new Gift().gift().name("Gift 1").price(33).build(),
                        new Gift().gift().name("Gift 2").price(21).build()
                ))
                .name("Noel")
                .build();

        when(giftListService.createGiftList(any(GiftList.class))).thenReturn(newGiftList);

        // Act && Arrange
        mockMvc.perform(post("/giftlist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newGiftList)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("6914b6d4efab04099f43878f"))
                .andExpect(jsonPath("$.name").value("Noel"))
                .andExpect(jsonPath("$.gifts.length()").value(2));

    }
}
