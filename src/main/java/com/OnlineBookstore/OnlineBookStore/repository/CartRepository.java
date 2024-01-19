package com.OnlineBookstore.OnlineBookStore.repository;

import com.OnlineBookstore.OnlineBookStore.entity.Book;
import com.OnlineBookstore.OnlineBookStore.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("select c from Cart c where c.bookId=:bookId")
    Optional<Cart> findByBookId(Long bookId);

    @Modifying
    @Transactional
    void deleteByCartId(Long cartId);

    @Query("select SUM(c.bookPrice) from Cart c")
    Double findTotalCartAmount();
}
