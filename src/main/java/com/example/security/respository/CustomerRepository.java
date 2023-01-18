package com.example.security.respository;

import com.example.security.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(name = "SELECT * FROM customer WHERE email = :email" , nativeQuery = true)
    Optional<Customer> getCustomerByEmail(@Param("email") String email);
}
