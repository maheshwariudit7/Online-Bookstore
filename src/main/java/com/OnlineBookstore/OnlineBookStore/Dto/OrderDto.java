package com.OnlineBookstore.OnlineBookStore.Dto;

import com.OnlineBookstore.OnlineBookStore.constants.OrderStatus;
import com.OnlineBookstore.OnlineBookStore.constants.PaymentMethod;
import com.OnlineBookstore.OnlineBookStore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {


    private long orderId;

    private long CustomerId;

    private LocalDateTime orderDate;

    private PaymentMethod paymentMethod;

    private Double totalAmnt;

    private OrderStatus orderStatus;
}
