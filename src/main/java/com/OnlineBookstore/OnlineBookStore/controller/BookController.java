package com.OnlineBookstore.OnlineBookStore.controller;

import com.OnlineBookstore.OnlineBookStore.Dto.BookDto;
import com.OnlineBookstore.OnlineBookStore.entity.Book;
import com.OnlineBookstore.OnlineBookStore.exception.BookNotFoundException;
import com.OnlineBookstore.OnlineBookStore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookstore/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CartController cartController;

    @GetMapping("/view")
    public String getAllBooks(Model model) {
        List<BookDto> books = bookService.findAllBooks();
        List<Book> cartBooks = new ArrayList<>();

        model.addAttribute("books", books);
        model.addAttribute("cartBooks", cartBooks);
        return "books";
    }

    @GetMapping("/{bookId}/view")
    public String getBookById(@PathVariable("bookId") Long bookId, Model model) throws BookNotFoundException {
        Optional<BookDto> bookDto = bookService.findBookById(bookId);
        model.addAttribute("book", bookDto.get());
        return "view_book";
    }

    @GetMapping("/{bookId}/addToCart")
    public String addBookToCart(@PathVariable("bookId") Long bookId, @AuthenticationPrincipal UserDetails userDetails) throws BookNotFoundException {
        bookService.addBookToCart(bookId);
        System.out.println(userDetails.getUsername());
        cartController.addToCart(bookId, userDetails.getUsername());
        return "redirect:/bookstore/books/view";
    }

    @GetMapping("/{bookId}/removeFromCart")
    public String removeBookFromCart(@PathVariable("bookId") Long bookId) throws BookNotFoundException {
        bookService.removeBookFromCart(bookId);
        cartController.removeFromCart(bookId);
        return "redirect:/bookstore/books/view";
    }


    @GetMapping("/booktitle")
    @ResponseBody
    public BookDto getBookByTitle(@RequestParam("title") String bookTitle) throws BookNotFoundException {

        return bookService.findBookByTitle(bookTitle);

    }

    @PostMapping
    @ResponseBody
    public BookDto addBook(@RequestBody Book book) throws HttpMessageNotReadableException {

        if (book == null) {
            throw new HttpMessageNotReadableException("No request body found!");
        }

        return bookService.addBook(book);
    }


    @GetMapping("/{bookId}/delete")
    public String deleteBookById(@PathVariable Long bookId) {

        bookService.deleteABook(bookId);
        return "redirect:/bookstore/books/view";

        // here the "/bookstore/books/view" is a mapping url where the users will be redirected
    }

    @GetMapping("{bookId}/edit")
    public String editBook(@PathVariable Long bookId,Model model) throws BookNotFoundException {
        Optional<BookDto> book=bookService.findBookById(bookId);
        model.addAttribute("book",book.get());
        return "edit_book";
    }

    @PostMapping("{bookId}/saveBook")
    public String saveBook(@PathVariable Long bookId,@Valid @ModelAttribute("book") Book book, BindingResult result,
                           Model model,
                           @AuthenticationPrincipal UserDetails userDetails){
        if(result.hasErrors()){
            model.addAttribute("book",book);
            return "edit_book";
        }
        book.setId(bookId);
        bookService.updateBook(book);

        return "redirect:/bookstore/books/view";
    }

}
