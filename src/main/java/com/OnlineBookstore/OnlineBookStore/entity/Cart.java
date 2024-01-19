package com.OnlineBookstore.OnlineBookStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    private Long userId;

    @NotEmpty
    private String userFirstName;
    private String userLastName;

    @NotNull
    private Long bookId;
    @NotEmpty
    private String bookTitle;
    @NotNull
    private double bookPrice;

}
