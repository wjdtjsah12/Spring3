package com.sparta.spring3.service;

import com.sparta.spring3.dto.FoodOptionRequestDto;
import com.sparta.spring3.dto.FoodOptionResponseDto;
import com.sparta.spring3.dto.FoodRequestDto;
import com.sparta.spring3.dto.FoodResponseDto;
import com.sparta.spring3.model.Food;
import com.sparta.spring3.model.FoodOption;
import com.sparta.spring3.model.Restaurant;
import com.sparta.spring3.repository.FoodOptionRepository;
import com.sparta.spring3.repository.FoodRepository;
import com.sparta.spring3.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodOptionRepository foodOptionRepository;

    // 음식점에 음식 추가 메소드
    public void createFood(Long restaurantId, List<FoodRequestDto> foodRequestDtoList) {
        // 레스토랑 정보 유효성 검사
        Optional <Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(!restaurant.isPresent()){
            throw new NullPointerException("존재하지 않는 음식점입니다.");
        }

        // 음식점별 음식을 추가
        List<Food> foodList = new ArrayList<>();
        for(FoodRequestDto foodDto : foodRequestDtoList){
            // 음식별 유효성 검사
            foodDtoValidCheck(restaurantId, foodDto, foodList);
        }

        // 음식 등록 진행
        for (FoodRequestDto foodDto : foodRequestDtoList){
            List<FoodOption> foodOptionList = new ArrayList<>();

            //음식 옵션 등록
            for (FoodOptionRequestDto foodOptionRequestDto : foodDto.getOption()){
                FoodOption foodOption = new FoodOption(foodOptionRequestDto);
                foodOptionList.add(foodOption);
            }

            if(foodOptionList.size() > 0){
                // 데이터베이스에 저정하면서 foodOptionList에 id값 추가
                foodOptionList = foodOptionRepository.saveAll(foodOptionList);
            }

            //음식 등록
            Food food = new Food(restaurant.get(), foodDto, foodOptionList);
            foodList.add(food);
        }

        if (foodRequestDtoList.size() > 0){
            foodRepository.saveAll(foodList);
        } else {
            throw new IllegalArgumentException("음식을 입력해주세요.");
        }
    }

    private void foodDtoValidCheck(Long restaurantId, FoodRequestDto foodDto, List<Food> foodList) {
        int price = foodDto.getPrice();

        Optional<Food> found = foodRepository.findByRestaurantIdAndFoodName(restaurantId, foodDto.getFoodName());
        if(found.isPresent()){
            throw new IllegalArgumentException("같은 이름의 음식은 추가할 수 없습니다.");
        } else if (price < 100 || price > 1000000){
            throw new IllegalArgumentException("음식 가격은 100원 ~ 1,000,000원 범위내에서 설정 가능합니다.");
        } else if (price % 100 != 0){
            throw new IllegalArgumentException("음식 가격은 100원 단위로만 입력 가능합니다.");
        }
        for (Food food : foodList){
            if(food.getFoodName().equals(foodDto.getFoodName())){
                throw new IllegalArgumentException("같은 이름의 음식은 추가할 수 없습니다.");
            }
        }
    }

    // 음식 조회
    public List<FoodResponseDto> getFoods(Long restaurantId) {
        // restaurantId로 찾은 Food 배열을 foods로 선언
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        //Food ResponseDto로 형변환 리스트 생성

        return foodDtoListSetting(foods);
    }

    private List<FoodResponseDto> foodDtoListSetting(List<Food> foods) {
        List<FoodResponseDto> result = new ArrayList<>();
        for(Food food : foods){
            List<FoodOptionResponseDto> foodOptionResponseDtos = foodOptionResponseDtoListSetting(food.getFoodOptionList());
            result.add(new FoodResponseDto(food, foodOptionResponseDtos));
        }
        return result;
    }

    private List<FoodOptionResponseDto> foodOptionResponseDtoListSetting(List<FoodOption> foodOptionList) {
        List<FoodOptionResponseDto> result = new ArrayList<>();

        for(FoodOption foodOption : foodOptionList){
            FoodOptionResponseDto foodOptionResponseDto = new FoodOptionResponseDto(foodOption);
            result.add(foodOptionResponseDto);
        }

        return result;
    }
}
