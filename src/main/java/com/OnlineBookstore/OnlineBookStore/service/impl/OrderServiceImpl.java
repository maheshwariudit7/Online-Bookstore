package com.OnlineBookstore.OnlineBookStore.service.impl;

import com.OnlineBookstore.OnlineBookStore.Dto.OrderDto;
import com.OnlineBookstore.OnlineBookStore.Mapper.OrderMapper;
import com.OnlineBookstore.OnlineBookStore.entity.Order;
import com.OnlineBookstore.OnlineBookStore.repository.OrderRepository;
import com.OnlineBookstore.OnlineBookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;



    @Override
    public OrderDto saveOrder(Order order) {
        orderRepository.save(order);
        OrderDto orderDto= OrderMapper.mapToOrderDto(order);
        return orderDto;
    }

    @Override
    public Optional<OrderDto> getByOrderId(Long orderId) {
        Optional<Order> order=orderRepository.findById(orderId);
        if(!order.isEmpty()){
            OrderDto orderDto=OrderMapper.mapToOrderDto(order.get());
            return Optional.of(orderDto);
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<OrderDto>> getAllOrders() {

        Optional<List<Order>> orders=Optional.of(orderRepository.findAll());
        if(!orders.isEmpty()){

            return Optional.of(orders.get().stream().map(order->OrderMapper.mapToOrderDto(order)).collect(Collectors.toList()));

        }

        return Optional.empty();

    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        List<Order> orders= orderRepository.findOrdersByUserId(userId);
        return orders.stream().map(o-> OrderMapper.mapToOrderDto(o)).collect(Collectors.toList());
    }

    @Override
    public void deleteOrderById(Long orderId) {

        if(orderRepository.findById(orderId).isEmpty()){
            throw new HttpMessageNotReadableException("Order not found");
        }
        orderRepository.deleteById(orderId);
    }



}
