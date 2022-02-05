package com.sparta.spring3.controller;

import com.sparta.spring3.dto.RestaurantDto;
import com.sparta.spring3.model.Restaurant;
import com.sparta.spring3.repository.RestaurantRepository;
import com.sparta.spring3.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;


    // 음식점 조회
    @GetMapping("/restaurant")
    public List<Restaurant> getRestaurant(){
        return restaurantRepository.findAll();
    }

    // 음식점 등록 처리
    @PostMapping("/restaurant/register")
    public Restaurant registerRestuarant(@RequestBody RestaurantDto requestDto) throws SQLException {
        return restaurantService.registerRestaurant(requestDto);
    }
}
