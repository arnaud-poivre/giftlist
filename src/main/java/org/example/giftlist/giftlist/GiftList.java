package org.example.giftlist.giftlist;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String name;
    private List<Gift> gifts;

    public GiftListBuilder giftList() {
        return GiftList.builder();
    }

}
