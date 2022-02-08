package com.sparta.spring3.model;

import com.sparta.spring3.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL)
    List<FoodOption> foodOptionList = new ArrayList<>();


    public Food(Restaurant restaurant, FoodRequestDto requestDto, List<FoodOption> foodOptionList){
        this.foodName = requestDto.getFoodName();
        this.price = requestDto.getPrice();
        this.restaurant = restaurant;
        this.foodOptionList = foodOptionList;
    }
}
