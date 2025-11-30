package com.example.vulnerable_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Endpoint de login vulnérable à l'injection SQL
    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        String query = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
        if (!results.isEmpty()) {
            return "Login successful";
        } else {
            return "Login failed";
        }
    }

    // Endpoint de register vulnérable (GET au lieu de POST, données dans URL)
    @GetMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        String query = "INSERT INTO user (username, password, email) VALUES ('" + username + "', '" + password + "', '" + email + "')";
        jdbcTemplate.update(query);
        return "User registered: " + username;
    }
}