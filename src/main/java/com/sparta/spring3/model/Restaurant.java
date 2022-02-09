package com.sparta.spring3.model;

import com.sparta.spring3.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 가게 이름은 반드시 입력
    @Column(nullable = false)
    private String name;

    //최소 주문 금액은 반드시 입력
    @Column(nullable = false)
    private int minOrderPrice;

    // 배송비는 반드시 입력.
    @Column(nullable = false)
    private int deliveryFee;

    public Restaurant(RestaurantDto requestDto){
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }

}
