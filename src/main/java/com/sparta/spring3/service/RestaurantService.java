package com.sparta.spring3.service;

import com.sparta.spring3.dto.RestaurantDto;
import com.sparta.spring3.model.Restaurant;
import com.sparta.spring3.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;


    public Restaurant registerRestaurant(RestaurantDto restaurantDto){
        // 음식점 유효성 검사 함수 진입
        validCheck(restaurantDto);

        // 통과 후 가게 등록 진행
        Restaurant restaurant = new Restaurant(restaurantDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    // 음식점 유효성 검사
    private void validCheck(RestaurantDto restaurantDto) {
        int minOrderPriceCheck = restaurantDto.getMinOrderPrice();
        int deliveryFeeCheck = restaurantDto.getDeliveryFee();

        // 유효성 검사
        if(minOrderPriceCheck < 1000 || minOrderPriceCheck > 100000){
            throw new IllegalArgumentException("최소 주문금액은 1,000원 ~ 100,000원 범위 내에서 설정 가능합니다.");
        }else if(minOrderPriceCheck % 100 != 0){
            throw new IllegalArgumentException("최소 주문금액은 100원 단위만 설정 가능합니다.");
        }else if(deliveryFeeCheck < 0 || deliveryFeeCheck > 10000){
            throw new IllegalArgumentException("기본 배달비는 0원 ~ 10,000원 범위 내에서 설정 가능합니다.");
        }else if(deliveryFeeCheck % 500 != 0){
            throw new IllegalArgumentException("기본 배달비는 500원 단위만 설정 가능합니다.");
        }
    }

    // 음식점 전체 조회 함수
    public List<Restaurant> getRestaurants() {
        // repository.findAll 리턴타입은 List<Restaurant>
        return restaurantRepository.findAll();
    }
}
