package com.example.springbatchdemo.repo;

import com.example.springbatchdemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
}
