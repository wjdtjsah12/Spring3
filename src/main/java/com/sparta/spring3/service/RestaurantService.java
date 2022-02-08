package com.sparta.spring3.service;

import com.sparta.spring3.dto.RestaurantResponseDto;
import com.sparta.spring3.model.Restaurant;
import com.sparta.spring3.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantResponseDto registerRestaurant(RestaurantResponseDto requestDto){
        // 음식점 유효성 검사
        int minOrderPriceCheck = requestDto.getMinOrderPrice();
        int deliveryFeeCheck = requestDto.getDeliveryFee();

        // 최소주문가격 입력조건
        if(minOrderPriceCheck < 1000 || minOrderPriceCheck > 100000){
            throw new IllegalArgumentException("최소 주문금액은 1,000원 ~ 100,000원 범위 내에서 설정 가능합니다.");
        }else if(minOrderPriceCheck % 100 != 0){
            throw new IllegalArgumentException("최소 주문금액은 100원 단위만 설정 가능합니다.");
        }

        // 기본배달비 입력조건
        if(deliveryFeeCheck < 0 || deliveryFeeCheck > 10000){
            throw new IllegalArgumentException("기본 배달비는 0원 ~ 10,000원 범위 내에서 설정 가능합니다.");
        }else if(deliveryFeeCheck % 500 != 0){
            throw new IllegalArgumentException("기본 배달비는 500원 단위만 설정 가능합니다.");
        }

        // 통과 후 가게 등록 진행
        Restaurant restaurant = restaurantRepository.save(new Restaurant(requestDto));
        return new RestaurantResponseDto(restaurant);
    }

    public List<RestaurantResponseDto> getRestaurants() {
        // 모든 음식적 목록 출력 메소드
        List <Restaurant> restaurantList = restaurantRepository.findAll();
        List<RestaurantResponseDto> response = new ArrayList<>();
        for(Restaurant restaurant : restaurantList){
            response.add(new RestaurantResponseDto(restaurant));
        }
        return response;
    }
}
