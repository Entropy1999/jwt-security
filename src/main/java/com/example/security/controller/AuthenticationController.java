package com.example.security.controller;

import com.example.security.model.Customer;
import com.example.security.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final CustomerService customerService;

    @Autowired
    public AuthenticationController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        String message = "User: " + customer.getEmail() + " was created";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
