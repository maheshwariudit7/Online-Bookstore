package com.OnlineBookstore.OnlineBookStore.controller;

import com.OnlineBookstore.OnlineBookStore.Dto.OrderDto;
import com.OnlineBookstore.OnlineBookStore.Dto.UserDto;
import com.OnlineBookstore.OnlineBookStore.constants.OrderStatus;
import com.OnlineBookstore.OnlineBookStore.constants.PaymentMethod;
import com.OnlineBookstore.OnlineBookStore.entity.Cart;
import com.OnlineBookstore.OnlineBookStore.entity.Order;
import com.OnlineBookstore.OnlineBookStore.entity.OrdersHelper;
import com.OnlineBookstore.OnlineBookStore.entity.User;
import com.OnlineBookstore.OnlineBookStore.exception.BookNotFoundException;
import com.OnlineBookstore.OnlineBookStore.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookstore/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartController cartController;

    @Autowired
    private BookController bookController;

    @Autowired
    private UserController userController;

    @Autowired
    private OrdersHelpersController ordersHelpersController;


    @GetMapping("/postOrder")
    @Transactional
    public String postOrder() throws BookNotFoundException {

        List<Cart> cartItems=cartController.getAllCartOrder();
        long userId=cartItems.get(0).getUserId();

        List<Order> orders=new ArrayList<>();
        for(Cart cart:cartItems){
            Order o=new Order();
            o.setCustomerId(userId);
            o.setBookId(cart.getBookId());
            o.setOrderDate(LocalDateTime.now());
            o.setOrderStatus(OrderStatus.PROCESSED);
            o.setPaymentMethod(PaymentMethod.DEBIT);
            o.setTotalAmnt(cartController.getCartTotalAmnt());
            o.setOrderNo(ordersHelpersController.getOrderNo());

            orderService.saveOrder(o);

            //once order placed removing book from cart
            cartController.removeCart(cart.getCartId());

            //setting inCart flag to false in books table to indicate book is no more in the cart
            bookController.removeBookFromCart(cart.getBookId());
        }

        // incrementing orderNo field in OrdersHelper table

        ordersHelpersController.incrementOrderNo();


        return "place_order";

    }


    @GetMapping("/{userId}/{orderId}/view")
    public String findAnOrder(@PathVariable Long orderId, Model model, @AuthenticationPrincipal UserDetails userDetails){

        //getting user id based on username from UserDetails class
        Optional<UserDto> user=userController.getUserByUsername(userDetails.getUsername());

        if(orderId.toString()==null){
            throw new HttpMessageNotReadableException("No order id supplied");
        }


        Optional<OrderDto> optionalOrderDto=orderService.getByOrderId(orderId);
        if(!optionalOrderDto.isEmpty()){
            OrderDto order=optionalOrderDto.get();
            model.addAttribute("order",order);

            //passing the fetched user id to the template so that if "MyOrders.html" is called while viewing a
            // specific order details so that the "MyOrders.html" can load all the user specific orders again as it
            // requires user id for that
            model.addAttribute("userIdFromController",user.get().getUserid());
            return "view_order";
        }
        throw new HttpMessageNotReadableException("No order exists with orderId");

    }

    @GetMapping("/view")
    public String findAllOrders(Model model){

        Optional<List<OrderDto>> optionalOrderDto=orderService.getAllOrders();
        List<OrderDto> orders=optionalOrderDto.get();
        model.addAttribute("orders",orders);
        return "orders";
    }

    @GetMapping("/user/{userId}/view")
    public String findUSerOrders(@PathVariable Long userId,Model model,@AuthenticationPrincipal UserDetails userDetails){

        Optional<UserDto> user=userController.getUserByUsername(userDetails.getUsername());


        List<OrderDto> orders=orderService.getUserOrders(userId);
        model.addAttribute("userIdFromController",user.get().getUserid());

        model.addAttribute("orders",orders);
        return "orders";
    }

    @GetMapping("/{orderId}/delete")
    public String deleteOrder(@PathVariable Long orderId){

        if(Optional.of(orderId).isEmpty()){
            throw new HttpMessageNotReadableException("No order id supplied");
        }


        orderService.deleteOrderById(orderId);
        return "redirect:/bookstore/orders/view";
    }
}
