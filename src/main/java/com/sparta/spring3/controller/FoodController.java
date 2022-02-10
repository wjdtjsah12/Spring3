package com.sparta.spring3.controller;

import com.sparta.spring3.dto.FoodDto;
import com.sparta.spring3.model.Food;
import com.sparta.spring3.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFood(@PathVariable Long restaurantId){
        return foodService.getFood(restaurantId);
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    // Return Type List<Food>에서 void로 변경
    public void createFood(@RequestBody List<FoodDto> foodDtos,
                                    @PathVariable Long restaurantId) throws Exception {
        foodService.createFood(foodDtos, restaurantId);
//        return null;
//        return type 변경으로 return 제거
    }
}
