package com.sparta.spring3.model;

import com.sparta.spring3.dto.FoodOptionRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class FoodOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String option;

    @Column(nullable = false)
    private int price;

    public FoodOption(FoodOptionRequestDto foodOptionRequestDto) {
        this.option = foodOptionRequestDto.getOption();
        this.price = foodOptionRequestDto.getPrice();
    }
}
