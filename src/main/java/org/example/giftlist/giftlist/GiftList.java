package org.example.giftlist.giftlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Le nom est obligatoire")
    private String name;

    @NotNull(message = "La liste des cadeaux est obligatoire")
    @Size(min = 1, message = "La liste des cadeaux ne peut pas être vide")
    @Valid
    private List<Gift> gifts;

    public GiftListBuilder giftList() {
        return GiftList.builder();
    }

}
