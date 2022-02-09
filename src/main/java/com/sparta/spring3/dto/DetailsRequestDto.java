package com.sparta.spring3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DetailsRequestDto {
    private Long id;
    private String name;
    private int price;
    private int quantity;

    @Builder
    public DetailsRequestDto(String name, int price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
