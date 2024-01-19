package com.OnlineBookstore.OnlineBookStore.service;

import com.OnlineBookstore.OnlineBookStore.Dto.BookDto;
import com.OnlineBookstore.OnlineBookStore.entity.Book;
import com.OnlineBookstore.OnlineBookStore.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDto> findAllBooks();

    Optional<BookDto> findBookById(Long bookId) throws BookNotFoundException;

    BookDto findBookByTitle(String bookTitle) throws BookNotFoundException;

    BookDto addBook(Book book);

    void deleteABook(Long bookId);

    void addBookToCart(Long bookId);

    void removeBookFromCart(Long bookId);
}
