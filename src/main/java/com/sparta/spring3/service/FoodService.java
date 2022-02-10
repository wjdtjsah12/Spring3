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
        // FindAllBy Return 타입은 List<Food>
        return foodRepository.findAllByRestaurantId(restaurantId);
    }

    public int createFood(FoodDto requestDto, Long restaurantId) throws Exception {
        String name = requestDto.getName();
        int price = requestDto.getPrice();
        List<Food> found = foodRepository.findAllByRestaurantIdAndName(restaurantId, name);
        // found 배열이 비어있지 않을때 == 0이 아닌 1이상일때는 Exception throw
        if(found.size() != 0){
            throw new Exception("중복되는 메뉴가 존재합니다.");
        }else if(price < 100 || price > 1000000){
            throw new Exception("메뉴 가격은 100원 ~ 1,000,000원 범위 내에서 입력해주세요.");
        }else if(price%100 != 0){
            throw new Exception("100원 단위로 입력해주세요.");
        }

        // 조건식 통과 시 해당 음식 DB에 추가합니다.
        Food food = new Food(requestDto, restaurantId);
        foodRepository.save(food);

        // 조건식 검사를 위해 found의 size return
        return found.size();

    }
}
