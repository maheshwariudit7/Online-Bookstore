package com.OnlineBookstore.OnlineBookStore.repository;

import com.OnlineBookstore.OnlineBookStore.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByTitle(String bookTitle);

    @Modifying
    @Transactional
    @Query("update Book b set b.inCart=true where b.id= :bookId")
    void addToCart(Long bookId);

    @Modifying
    @Transactional
    @Query("update Book b set b.inCart=false where b.id= :bookId")
    void removeFromCart(Long bookId);

//    @Modifying
//    @Transactional
//    @Query("update Book b set")
//    void updateBook(Book book);
}
