package com.sparta.spring3.repository;

import com.sparta.spring3.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA를 상속하고 Restaurant 형식 table과 Long 타입으로 PK를 받는다.
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
