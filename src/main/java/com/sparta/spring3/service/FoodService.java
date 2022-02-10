package com.sparta.spring3.service;

import com.sparta.spring3.dto.FoodDto;
import com.sparta.spring3.model.Food;
import com.sparta.spring3.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public List<Food> getFood(Long restaurantId) {
        // FindAllBy Return 타입은 List<Food>
        return foodRepository.findAllByRestaurantId(restaurantId);
    }

    public int registerFood(FoodDto requestDto, Long restaurantId) throws Exception {
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

        // 조건식 통과 시 해당 음식을 DB에 추가합니다.
        Food food = new Food(requestDto, restaurantId);
        foodRepository.save(food);

        // 조건식 검사를 위해 found의 size return
        return found.size();

    }

    public void createFood(List<FoodDto> foodDtos, Long restaurantId) throws Exception {

        List<String> foodNames = new ArrayList<>();
        for(FoodDto foodDto : foodDtos){
            // foodDtos의 이름값을 foodNames 배열에 순차적으로 저장합니다.
            foodNames.add(foodDto.getName());
        }

        // 리스트 내 중복체크
        if(foodNames.size() != foodNames.stream().distinct().count()){
            // foodNames 배열의 길이와 중복제거를 한 stream 메소드의 값을 count한 값이 같은지 비교합니다.
            // stream 메소드의 함수 distinct는 중복을 제거합니다.
            // stream 종결함수 count는 원소 갯수를 카운트해서 long 타입으로 리턴합니다.
            throw new Exception("중복되는 메뉴가 존재합니다.");
        }

        // 조건문 통과 후 foodDtos 배열에 들어있는 값들은 DB에 순차적으로 등록합니다.
        for(FoodDto foodDto : foodDtos){
            int found = registerFood(foodDto, restaurantId);
            // 중복되는 음식명을 기입할때 반복문 break
            if(found != 0){
                break;
            }
        }
    }
}
