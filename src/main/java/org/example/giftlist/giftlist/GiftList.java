package org.example.giftlist.giftlist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
