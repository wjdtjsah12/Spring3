package com.sparta.spring3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestDto {
    private Long id;
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
