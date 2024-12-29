package com.example.AttachNet.api.repository;

import com.example.AttachNet.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    // JpaRepository provides built-in CRUD methods like save(), findById(), deleteById(), etc.
}