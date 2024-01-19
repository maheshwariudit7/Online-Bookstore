package com.OnlineBookstore.OnlineBookStore.controller;

import com.OnlineBookstore.OnlineBookStore.service.OrdersHelperService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrdersHelpersController {

    @Autowired
    private OrdersHelperService ordersHelperService;

    public long getOrderNo(){
        return ordersHelperService.getOrderNo();
    }

    public void incrementOrderNo(){
        ordersHelperService.incrementOrderNo();
    }
}
