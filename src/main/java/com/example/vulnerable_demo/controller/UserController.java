package com.example.vulnerable_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Exemple d'endpoint pour récupérer un utilisateur (vulnérable à l'injection si mal utilisé)
    @GetMapping("/user")
    public Map<String, Object> getUser(@RequestParam String username) {
        String query = "SELECT * FROM user WHERE username='" + username + "'";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        if (result.isEmpty()) {
            return Map.of("error", "User not found");
        }

        Map<String, Object> user = result.getFirst();
        user.remove("password"); // 🚀 SUPPRESSION DU MOT DE PASSE

        return user;
    }
}