package com.example.petstore.repositiry;

import com.example.petstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getByUserName(String userName);
}
