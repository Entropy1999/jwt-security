package com.example.security.security;

import com.example.security.model.Customer;
import com.example.security.service.CustomerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomerService customerService;

    public CustomAuthenticationManager(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder, @Lazy CustomerService customerService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customerService = customerService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Customer customer = customerService.getCustomerByEmail(authentication.getName());
        if (bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), customer.getPassword())) {
            return new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword());
        }
        throw new BadCredentialsException("BAD CREDENTIALS");
    }
}
