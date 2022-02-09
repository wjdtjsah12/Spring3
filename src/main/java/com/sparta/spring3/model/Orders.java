package com.sparta.spring3.model;

import com.sparta.spring3.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Orders {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 이 주문에 해당하는 음식점 이름은 반드시 입력
    @Column(nullable = false)
    private String restaurantName;

    // 음식 이름은 반드시 입력
    @OneToMany
    private List<OrderDetails> foods;

    // 음식 가격은 반드시 입력
    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    public Orders(String restaurantName, List<OrderDetails> foods,
                  int deliveryFee, int totalPrice){
        this.restaurantName = restaurantName;
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }
}
