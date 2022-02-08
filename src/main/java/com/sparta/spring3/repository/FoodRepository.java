package com.sparta.spring3.repository;

import com.sparta.spring3.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByRestaurantIdAndFoodName(Long restaurantId, String foodName);
    List<Food> findByRestaurantId(Long restaurantId);
}
