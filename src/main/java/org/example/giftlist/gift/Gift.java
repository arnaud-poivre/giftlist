package org.example.giftlist.gift;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gift {

    private String name;
    private int price;

    public GiftBuilder gift() {
        return Gift.builder();
    }
}
