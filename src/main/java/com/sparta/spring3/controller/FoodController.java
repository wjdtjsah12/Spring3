package com.sparta.spring3.controller;

import com.sparta.spring3.dto.FoodDto;
import com.sparta.spring3.model.Food;
import com.sparta.spring3.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<FoodDto> createFood(@RequestBody List<FoodDto> foodDtos,
                                    @PathVariable Long restaurantId) throws Exception {
        List<String> foodNames = new ArrayList<String>();
        for(FoodDto foodDto : foodDtos){
            foodNames.add(foodDto.getName());
        }

        // 리스트 내 중복체크
        if(foodNames.size() != foodNames.stream().distinct().count()){
            // stream 클래스의 함수 distinct는 중복을 제거합니다.
            // stream 종결함수 count는 원소 갯수를 카운트해서 long 타입으로 리턴합니다.
            throw new Exception("중복되는 메뉴가 존재합니다.");
        }

        for(FoodDto foodDto : foodDtos){
            int found = foodService.createFood(foodDto, restaurantId);
            if(found != 0){
                break;
            }
        }
        return null;
    }
}
