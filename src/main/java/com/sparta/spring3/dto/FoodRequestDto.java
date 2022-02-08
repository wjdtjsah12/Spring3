package com.sparta.spring3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
// 클래스에 존재하는 모든 필드에 대한 생성자를 생성해줍니다.
@NoArgsConstructor
// 파라미터가 없는 생성자를 생성합니다.
public class FoodRequestDto {
    private Long id;
    private String foodName;
    private int price;
    private List<FoodOptionRequestDto> option = new ArrayList<>();
}
