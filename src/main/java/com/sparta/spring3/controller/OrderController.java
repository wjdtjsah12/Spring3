package com.sparta.spring3.controller;

import com.sparta.spring3.dto.DetailsRequestDto;
import com.sparta.spring3.dto.OrderRequestDto;
import com.sparta.spring3.model.Orders;
import com.sparta.spring3.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/request")
    public Orders addOrders(@RequestBody OrderRequestDto requestDto) throws Exception {
        Long restaurantId = requestDto.getRestaurantId();
        List<DetailsRequestDto> orderListDto = requestDto.getFoods();
        return orderService.addOrders(orderListDto, restaurantId);
    }

    @GetMapping("/orders")
    public List<Orders> getOrders() {return orderService.getOrders();}
}
