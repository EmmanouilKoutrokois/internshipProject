package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CompanyService {

    CompanyDTO save(CompanyDTO dto);

    List<CompanyDTO> findAll();

    Optional<CompanyDTO> findById(Long id);

    CompanyDTO update(Long id, CompanyDTO dto);

    void deleteById(Long id);

    Double getTotalSalaryForCompany(Long companyId);

    Company findEntityById(Long companyId);

    Map<String, List<Product>> getEmployeeProducts(Long companyId);
}