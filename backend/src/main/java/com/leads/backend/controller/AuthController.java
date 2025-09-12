package com.leads.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

  @GetMapping("/login")
  public String login(@RequestParam String id, @RequestParam String password) {
    if ("1".equals(id) && "1".equals(password)) {
      return "Redirecting to registration page";
    } else {
      return "Invalid credentials";
    }
  }

}
