package com.OnlineBookstore.OnlineBookStore.service.impl;

import com.OnlineBookstore.OnlineBookStore.entity.OrdersHelper;
import com.OnlineBookstore.OnlineBookStore.repository.OrdersHelperRepository;
import com.OnlineBookstore.OnlineBookStore.service.OrdersHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersHelperServiceImpl implements OrdersHelperService {

    @Autowired
    private OrdersHelperRepository ordersHelperRepository;
    @Override
    public Long getOrderNo() {

        return ordersHelperRepository.getOrderNo();
    }

    @Override
    public void incrementOrderNo() {

        ordersHelperRepository.incrementOrderNo();

    }
}
