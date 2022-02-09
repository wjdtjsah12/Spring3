package com.sparta.spring3.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class RestaurantDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
