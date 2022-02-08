package com.sparta.spring3.dto;

import com.sparta.spring3.model.FoodOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodOptionResponseDto {
    private String option;
    private int price;

    public FoodOptionResponseDto(FoodOption foodOption) {
        this.option = foodOption.getOption();
        this.price = foodOption.getPrice();
    }
}
