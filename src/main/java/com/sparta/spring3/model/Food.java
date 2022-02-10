package com.sparta.spring3.model;


import com.sparta.spring3.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 해당 음식의 음식점 아이디는 반드시 입력
    @Column(nullable = false)
    private Long restaurantId;

    // 음식 이름은 반드시 입력
    @Column(nullable = false)
    private String name;

    // 음식 가격은 반드시 입력
    @Column(nullable = false)
    private int price;

    public Food(FoodDto requestDto, Long restaurantId){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurantId = restaurantId;
    }
}
