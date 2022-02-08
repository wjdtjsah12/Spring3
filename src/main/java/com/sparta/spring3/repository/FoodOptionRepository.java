package com.sparta.spring3.repository;

import com.sparta.spring3.model.FoodOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOptionRepository extends JpaRepository<FoodOption, Long> {
}
