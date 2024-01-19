package com.OnlineBookstore.OnlineBookStore.Mapper;

import com.OnlineBookstore.OnlineBookStore.Dto.OrderDto;
import com.OnlineBookstore.OnlineBookStore.entity.Order;

public class OrderMapper {

    public static OrderDto mapToOrderDto(Order order){
        OrderDto orderDto=new OrderDto();
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setOrderId(order.getOrderId());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setTotalAmnt(order.getTotalAmnt());
        orderDto.setPaymentMethod(order.getPaymentMethod());
        return orderDto;
    }

    public static Order mapToOrder(OrderDto orderDto){
        Order order=new Order();
        order.setOrderDate(orderDto.getOrderDate());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setOrderId(orderDto.getOrderId());
        order.setCustomerId(orderDto.getCustomerId());
        order.setTotalAmnt(orderDto.getTotalAmnt());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        return order;
    }
}
