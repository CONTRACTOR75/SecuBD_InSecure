package com.example.vulnerable_demo.repository;

import com.example.vulnerable_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Pour la version vulnérable, on utilise principalement JdbcTemplate pour les démos d'injection
    // Mais ce repo peut être utilisé pour d'autres opérations si needed
}