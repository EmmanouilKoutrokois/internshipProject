package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.repository.CompanyRepository;
import com.example.employeeManagement.repository.EmployeeRepository;
import com.example.employeeManagement.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CompanyDTO save(CompanyDTO dto) {
        // your existing implementation
        return null;
    }

    @Override
    public List<CompanyDTO> findAll() {
        // your existing implementation
        return null;
    }

    @Override
    public Optional<CompanyDTO> findById(Long id) {
        // your existing implementation
        return Optional.empty();
    }

    @Override
    public CompanyDTO update(Long id, CompanyDTO dto) {
        // your existing implementation
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // your existing implementation
    }

    // ⭐ NEW CUSTOM METHOD
    @Override
    public Double getTotalSalaryForCompany(Long companyId) {
        Double sum = employeeRepository.sumSalariesByCompanyId(companyId);
        return sum != null ? sum : 0.0;
    }
}
