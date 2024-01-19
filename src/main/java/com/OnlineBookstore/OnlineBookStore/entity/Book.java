package com.OnlineBookstore.OnlineBookStore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title can not be empty")
    private String title;
    private String description;

    @NotEmpty(message = "Author can not be empty")
    private String author;

    private String publisher;
    private LocalDate publicationDate;
    private int numberOfPages;
    private double price;


    private boolean inCart;

}
