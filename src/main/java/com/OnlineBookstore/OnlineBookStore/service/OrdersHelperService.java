package com.OnlineBookstore.OnlineBookStore.service;

import com.OnlineBookstore.OnlineBookStore.entity.OrdersHelper;

public interface OrdersHelperService {

    Long getOrderNo();

    void incrementOrderNo();
}
