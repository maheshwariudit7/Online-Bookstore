package com.OnlineBookstore.OnlineBookStore.service;

import com.OnlineBookstore.OnlineBookStore.Dto.OrderDto;
import com.OnlineBookstore.OnlineBookStore.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void deleteOrderById(Long orderId);

    OrderDto saveOrder(Order order);

    Optional<OrderDto> getByOrderId(Long orderId);

    Optional<List<OrderDto>> getAllOrders();

    List<OrderDto> getUserOrders(Long userId);
}
