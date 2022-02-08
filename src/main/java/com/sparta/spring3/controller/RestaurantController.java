package com.sparta.spring3.controller;

import com.sparta.spring3.dto.RestaurantRequestDto;
import com.sparta.spring3.dto.RestaurantResponseDto;
import com.sparta.spring3.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
// 의존성 주입을 위한 어노테이션, 생성자 주입이라고 한다.
// 추가작업을 필요로 하는 필드에 생성자를 생성하는 어노테이션
@RestController
// JSON 형태로 객체 데이터를 반환하는 컨트롤러에 사용하는 어노테이션
public class RestaurantController {

    private final RestaurantService restaurantService;


    // 음식점 조회
    @GetMapping("/restaurants")
    public List<RestaurantResponseDto> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    // 음식점 등록 처리
    @PostMapping("/restaurant/register")
    public RestaurantResponseDto registerRestaurant(@RequestBody RestaurantRequestDto restaurantDto) {
        return restaurantService.registerRestaurant(restaurantDto);
    }
}
