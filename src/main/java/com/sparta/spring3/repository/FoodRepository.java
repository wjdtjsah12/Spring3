package com.sparta.spring3.repository;

import com.sparta.spring3.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurantId(Long restaurantId);

    List<Food> findAllByRestaurantIdAndName(Long restaurantId, String name);
}
