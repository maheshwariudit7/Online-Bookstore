package com.OnlineBookstore.OnlineBookStore.service.impl;

import com.OnlineBookstore.OnlineBookStore.Dto.BookDto;
import com.OnlineBookstore.OnlineBookStore.Mapper.BookMapper;
import com.OnlineBookstore.OnlineBookStore.entity.Book;
import com.OnlineBookstore.OnlineBookStore.exception.BookNotFoundException;
import com.OnlineBookstore.OnlineBookStore.repository.BookRepository;
import com.OnlineBookstore.OnlineBookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<BookDto> findAllBooks() {
        List<Book> books=bookRepository.findAll();
        return books.stream().map(book-> BookMapper.mapToBookDto(book)).collect(Collectors.toList());
    }

    @Override
    public Optional<BookDto> findBookById(Long bookId) throws BookNotFoundException{
        Optional<Book> book=bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new BookNotFoundException(bookId);
        }
        BookDto bookDto=BookMapper.mapToBookDto(book.get());
        return Optional.of(bookDto);
    }

    @Override
    public BookDto findBookByTitle(String bookTitle) throws BookNotFoundException{
        Optional<Book> book=bookRepository.findByTitle(bookTitle);
        if(book.isEmpty()){
            throw new BookNotFoundException(bookTitle);
        }

        BookDto bookDto=BookMapper.mapToBookDto(book.get());
        return bookDto;

    }

    @Override
    public BookDto addBook(Book book) {
        bookRepository.save(book);
        BookDto bookDto=BookMapper.mapToBookDto(book);
        return bookDto;
    }

    @Override
    public void deleteABook(Long bookId) {
        if(bookRepository.findById(bookId).isEmpty()){
            throw new HttpMessageNotReadableException("Book with book id "+bookId + " was not found");
        }
        bookRepository.deleteById(bookId);
    }

    @Override
    public void addBookToCart(Long bookId) {
        bookRepository.addToCart(bookId);
    }

    @Override
    public void removeBookFromCart(Long bookId) {
        bookRepository.removeFromCart(bookId);
    }


}
