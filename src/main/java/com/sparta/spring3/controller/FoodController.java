package com.sparta.spring3.controller;

import com.sparta.spring3.dto.FoodRequestDto;
import com.sparta.spring3.dto.FoodResponseDto;
import com.sparta.spring3.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
// 의존성 주입을 위한 어노테이션, 생성자 주입이라고 한다.
// 추가작업을 필요로 하는 필드에 생성자를 생성하는 어노테이션
@RestController
// JSON 형태로 객체 데이터를 반환하는 컨트롤러에 사용하는 어노테이션
public class FoodController {

    private final FoodService foodService;

    // 메뉴판 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDto> getFoods(@PathVariable Long restaurantId){
        return foodService.getFoods(restaurantId);
    }

    // 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void createFood(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> requestDto){
        foodService.createFood(restaurantId, requestDto);
    }
}
