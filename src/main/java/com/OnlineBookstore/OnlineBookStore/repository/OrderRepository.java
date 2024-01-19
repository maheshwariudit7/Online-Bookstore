package com.OnlineBookstore.OnlineBookStore.repository;

import com.OnlineBookstore.OnlineBookStore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("Select o from Order o where o.customerId= :userId Order By o.orderDate desc")
    List<Order> findOrdersByUserId(Long userId);
}
