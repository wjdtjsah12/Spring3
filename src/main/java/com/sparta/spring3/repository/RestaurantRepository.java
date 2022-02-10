package com.sparta.spring3.repository;

import com.sparta.spring3.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA를 상속하고,
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
