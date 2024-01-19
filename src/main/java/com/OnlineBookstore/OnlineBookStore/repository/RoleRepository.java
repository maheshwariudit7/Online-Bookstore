package com.OnlineBookstore.OnlineBookStore.repository;

import com.OnlineBookstore.OnlineBookStore.entity.Role;
import com.OnlineBookstore.OnlineBookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
