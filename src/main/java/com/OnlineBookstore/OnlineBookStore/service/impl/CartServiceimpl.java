package com.OnlineBookstore.OnlineBookStore.service.impl;

import com.OnlineBookstore.OnlineBookStore.Dto.BookDto;
import com.OnlineBookstore.OnlineBookStore.Mapper.BookMapper;
import com.OnlineBookstore.OnlineBookStore.entity.Book;
import com.OnlineBookstore.OnlineBookStore.entity.Cart;
import com.OnlineBookstore.OnlineBookStore.exception.BookNotFoundException;
import com.OnlineBookstore.OnlineBookStore.repository.CartRepository;
import com.OnlineBookstore.OnlineBookStore.service.BookService;
import com.OnlineBookstore.OnlineBookStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceimpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookService bookService;
    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> findCartItemByBookId(Long bookId) throws BookNotFoundException {
        Optional<Cart> cart = cartRepository.findByBookId(bookId);

        if (cart.isEmpty() == false) {

            return cart;
        }
        else{
            throw new HttpMessageNotReadableException("cart not found");
        }
    }

    @Override
    public void removeFromCart(Long cartId) {
        cartRepository.deleteByCartId(cartId);
    }

    @Override
    public List<Cart> getMyCartItems() {
        List<Cart> c=cartRepository.findAll();
        return c;
    }

    @Override
    public Double TotalCartAmount() {

        return cartRepository.findTotalCartAmount();
    }

    @Override
    public void removeCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
