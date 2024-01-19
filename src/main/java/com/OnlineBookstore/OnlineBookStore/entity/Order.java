package com.OnlineBookstore.OnlineBookStore.entity;

import com.OnlineBookstore.OnlineBookStore.constants.OrderStatus;
import com.OnlineBookstore.OnlineBookStore.constants.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @NotNull
    private long orderNo;

    @NotNull(message = "Customer Id can not be empty")

//    @JoinColumn(name = "CustomerId",referencedColumnName = "userid")
    private long customerId;

    @NotNull
    private long bookId;

    @CreationTimestamp
    @NotNull(message = "Order date can not be empty")
    private LocalDateTime orderDate;

    @NotNull(message = "Payment method can not be empty")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @NotNull(message = "Total amount can not be empty")
    private Double totalAmnt;

    @NotNull(message = "Order status can not be empty")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "cartId", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Cart> cartItems;

}
