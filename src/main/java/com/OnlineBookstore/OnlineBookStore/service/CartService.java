package com.OnlineBookstore.OnlineBookStore.service;

import com.OnlineBookstore.OnlineBookStore.Dto.BookDto;
import com.OnlineBookstore.OnlineBookStore.entity.Cart;
import com.OnlineBookstore.OnlineBookStore.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CartService {

    void saveCart(Cart cart);

    Optional<Cart> findCartItemByBookId(Long bookId) throws BookNotFoundException;

    void removeFromCart(Long cartId);

    List<Cart> getMyCartItems();

    Double TotalCartAmount();

    void removeCart(Long cartId);
}
