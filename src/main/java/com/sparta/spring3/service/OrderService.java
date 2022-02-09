package com.sparta.spring3.service;

import com.sparta.spring3.dto.DetailsRequestDto;
import com.sparta.spring3.model.Food;
import com.sparta.spring3.model.OrderDetails;
import com.sparta.spring3.model.Orders;
import com.sparta.spring3.model.Restaurant;
import com.sparta.spring3.repository.FoodRepository;
import com.sparta.spring3.repository.OrderDetailsRepository;
import com.sparta.spring3.repository.OrderRepository;
import com.sparta.spring3.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderDetailsRepository orderDetailsRepository;


    public Orders addOrders(List<DetailsRequestDto> detailsRequestDtos, Long restaurantId) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(!restaurant.isPresent()){
            throw new Exception("등록되지 않은 음식점 입니다.");
        }
        int totalPrice = 0;

        List <OrderDetails> orderDetailsList = new ArrayList<>();
        // 데이터 입력 리스트

        for (DetailsRequestDto order : detailsRequestDtos){
            Optional<Food> food = foodRepository.findOneByRestaurantIdAndId(restaurantId, order.getId());
            if(!food.isPresent()){
                throw new Exception("등록되지 않은 메뉴 입니다.");
            }
            String foodName = food.get().getName();
            int quantity = order.getQuantity();
            if(order.getQuantity() < 1 || order.getQuantity() > 100){
                throw new Exception("주문 가능 수량을 초과합니다.");
            }

            int price = order.getQuantity() * food.get().getPrice();
            totalPrice += price;

            OrderDetails orderDetail = new OrderDetails(foodName, quantity, price);
            orderDetailsRepository.save(orderDetail);
            orderDetailsList.add(orderDetail);
        }
        if(totalPrice < restaurant.get().getMinOrderPrice()){
            throw new Exception("최소 주문가격을 넘지 않습니다.");
        }

        totalPrice += restaurant.get().getDeliveryFee();

        Orders orders = new Orders(restaurant.get().getName(), orderDetailsList,
                restaurant.get().getDeliveryFee(), totalPrice);

        orderRepository.save(orders);

        return orders;
    }

    @Transactional
    public List<Orders> getOrders() {
        return orderRepository.findAll();
    }
}
