package com.sparta.spring3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
