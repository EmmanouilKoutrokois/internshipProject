package com.example.employeeManagement.repository;

import com.example.employeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT SUM(e.salary) FROM Employee e WHERE e.company.id = :companyId")
    Double sumSalariesByCompanyId(@Param("companyId") Long companyId);
}