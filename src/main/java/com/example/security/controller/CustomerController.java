package com.example.security.controller;

import com.example.security.model.Customer;
import com.example.security.model.Role;
import com.example.security.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<List<Role>> getCustomerRoles(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id).getRoles(), HttpStatus.OK);
    }

    @GetMapping("/home")
    public ResponseEntity<String> homePage(@RequestParam(required = false, value = "logout") boolean isLoggedOut) {
        if (isLoggedOut) {
            return new ResponseEntity<>("Successfully Logged Out", HttpStatus.OK);
        }
        return new ResponseEntity<>("This is the public home page", HttpStatus.OK);
    }

}
