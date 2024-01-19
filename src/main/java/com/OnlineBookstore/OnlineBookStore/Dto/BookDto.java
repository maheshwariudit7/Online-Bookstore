package com.OnlineBookstore.OnlineBookStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private LocalDate publicationDate;
    private int numberOfPages;
    private double price;
    private boolean inCart;
}
