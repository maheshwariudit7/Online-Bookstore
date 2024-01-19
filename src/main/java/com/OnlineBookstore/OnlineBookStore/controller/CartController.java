package com.OnlineBookstore.OnlineBookStore.controller;

import com.OnlineBookstore.OnlineBookStore.Dto.BookDto;
import com.OnlineBookstore.OnlineBookStore.Dto.UserDto;
import com.OnlineBookstore.OnlineBookStore.entity.Cart;
import com.OnlineBookstore.OnlineBookStore.exception.BookNotFoundException;
import com.OnlineBookstore.OnlineBookStore.service.BookService;
import com.OnlineBookstore.OnlineBookStore.service.CartService;
import com.OnlineBookstore.OnlineBookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("bookstore/books/cart/{bookId}/{username}/addToCart")
    public void addToCart(@PathVariable Long bookId, @PathVariable String username) throws BookNotFoundException {
        Optional<BookDto> book=bookService.findBookById(bookId);
        Optional<UserDto> user=userService.getUserByUsername(username);

        Cart cart = new Cart();
        cart.setBookId(book.get().getId());
        cart.setBookPrice(book.get().getPrice());
        cart.setBookTitle(book.get().getTitle());
        cart.setUserId(user.get().getUserid());
        cart.setUserFirstName(user.get().getFirstName());
        cart.setUserLastName(user.get().getLastName());
        cartService.saveCart(cart);

    }

    //below method deletes a cart item based on bookId
    @GetMapping("bookstore/books/cart/{bookId}/{userId}/removeFromCart")
    public void removeFromCart(@PathVariable Long bookId) throws BookNotFoundException {

        Optional<Cart> cartItem=cartService.findCartItemByBookId(bookId);
        cartService.removeFromCart(cartItem.get().getCartId());

    }

    //below method deletes a cart based on cartId
    @GetMapping("bookstore/books/cart/{cartId}/removeCart")
    public void removeCart(@PathVariable Long cartId){

    }



    @GetMapping("/bookstore/books/cart/createCart")
    public String createCart(Model model){
        List<Cart> myCart=cartService.getMyCartItems();
        model.addAttribute("myCart",myCart);
        return "cart";
    }

    public List<Cart> getAllCartOrder(){

        List<Cart> cartItems=cartService.getMyCartItems();
        return cartItems;
    }

    public Double getCartTotalAmnt(){

        return cartService.TotalCartAmount();
    }




}
