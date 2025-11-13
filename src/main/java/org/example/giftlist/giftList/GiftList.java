package org.example.giftlist.giftList;

import lombok.*;
import org.example.giftlist.gift.Gift;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "giftlist")
public class GiftList {

    @Id
    private String id;
    private String name;
    private List<Gift> gifts;


    public GiftListBuilder giftList() {
        return GiftList.builder();
    }

}
