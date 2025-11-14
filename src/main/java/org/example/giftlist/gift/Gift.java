package org.example.giftlist.gift;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gift {

    @NotBlank(message = "Le nom du cadeau est obligatoire")
    private String name;

    @NotNull(message = "Le prix est obligatoire")
    @Min(value = 0, message = "Le prix doit être positif")
    private int price;

    public GiftBuilder gift() {
        return Gift.builder();
    }
}
