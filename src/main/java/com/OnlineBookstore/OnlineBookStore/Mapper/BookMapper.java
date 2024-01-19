package com.OnlineBookstore.OnlineBookStore.Mapper;

import com.OnlineBookstore.OnlineBookStore.Dto.BookDto;
import com.OnlineBookstore.OnlineBookStore.entity.Book;

public class BookMapper {

    public static BookDto mapToBookDto(Book book){
        BookDto bookDto=new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPrice(book.getPrice());
        bookDto.setDescription(book.getDescription());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setPublicationDate(book.getPublicationDate());
        bookDto.setNumberOfPages(book.getNumberOfPages());
        bookDto.setInCart(book.isInCart());
        return bookDto;
    }

    public static Book mapToBook(BookDto bookDto){
        Book book=new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        book.setDescription(bookDto.getDescription());
        book.setPublisher(bookDto.getPublisher());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setNumberOfPages(bookDto.getNumberOfPages());
        book.setInCart(bookDto.isInCart());
        return book;
    }


}
