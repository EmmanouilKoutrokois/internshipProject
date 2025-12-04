package com.example.employeeManagement.repository;

import com.example.employeeManagement.entity.EmployeeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProductRepository extends JpaRepository<EmployeeProduct, Long> { }