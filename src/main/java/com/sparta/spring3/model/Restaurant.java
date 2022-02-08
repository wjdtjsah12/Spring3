package com.sparta.spring3.model;

import com.sparta.spring3.dto.RestaurantRequestDto;
import com.sparta.spring3.dto.RestaurantResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 가게 이름은 반드시 입력
    @Column(nullable = true)
    private String name;

    //최소 주문 금액은 반드시 입력
    @Column(nullable = true)
    private int minOrderPrice;

    // 배송비는 반드시 입력.
    @Column(nullable = true)
    private int deliveryFee;

    @OneToMany(mappedBy = "restaurant")
    private List<Food> foodList;

    public Restaurant(RestaurantRequestDto requestDto){
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }

}
