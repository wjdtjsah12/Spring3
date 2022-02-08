package com.sparta.spring3.dto;

import com.sparta.spring3.model.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodResponseDto {
    private Long id;
    private String foodName;
    private int price;
    private List<FoodOptionResponseDto> option;

    public FoodResponseDto(Food food, List<FoodOptionResponseDto> option){
        this.id = food.getId();
        this.foodName = food.getFoodName();
        this.price = food.getPrice();
        this.option = option;
    }
}
