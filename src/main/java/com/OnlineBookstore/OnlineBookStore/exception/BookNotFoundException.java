package com.OnlineBookstore.OnlineBookStore.exception;

public class BookNotFoundException extends Throwable {

    private String bookTitle;
    private Long bookId;

    public BookNotFoundException(String bookTitle){
        super(String.format("Book with title %s is not found",bookTitle));
        this.bookTitle=bookTitle;

    }

    public BookNotFoundException(Long bookId){
        super(String.format("Book with Id %d is not found",bookId));
        this.bookId=bookId;

    }
}
