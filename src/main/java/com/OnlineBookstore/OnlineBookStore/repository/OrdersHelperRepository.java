package com.OnlineBookstore.OnlineBookStore.repository;

import com.OnlineBookstore.OnlineBookStore.entity.OrdersHelper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalLong;

@Repository
public interface OrdersHelperRepository extends JpaRepository<OrdersHelper,Long> {

    @Query("Select oh.orderNo from OrdersHelper oh")
    Long getOrderNo();

    @Query("Update OrdersHelper oh set oh.orderNo=oh.orderNo+1")
    @Modifying
    @Transactional
    void incrementOrderNo();


}
