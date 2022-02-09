package com.sparta.spring3.service;

import com.sparta.spring3.dto.FoodDto;
import com.sparta.spring3.model.Food;
import com.sparta.spring3.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public List<Food> getFood(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }

    public int createFood(FoodDto requestDto, Long restaurantId) throws Exception {
        String name = requestDto.getName();
        int price = requestDto.getPrice();
        List<Food> found = foodRepository.findAllByRestaurantIdAndName(restaurantId, name);

        if(found.size() != 0){
            throw new Exception("중복되는 메뉴가 존재합니다.");
        }else if(price < 100 || price > 1000000){
            throw new Exception("메뉴 가격은 100원 ~ 1,000,000원 범위 내에서 입력해주세요.");
        }else if(price%100 != 0){
            throw new Exception("100원 단위로 입력해주세요.");
        }

        Food food = new Food(requestDto, restaurantId);
        foodRepository.save(food);

        return found.size();

    }
}
