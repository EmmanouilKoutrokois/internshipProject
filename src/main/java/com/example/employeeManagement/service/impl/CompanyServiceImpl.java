package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.entity.Employee;
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
        // existing implementation
        return null;
    }

    @Override
    public List<CompanyDTO> findAll() {
        // existing implementation
        return null;
    }

    @Override
    public Optional<CompanyDTO> findById(Long id) {
        // existing implementation
        return Optional.empty();
    }

    @Override
    public CompanyDTO update(Long id, CompanyDTO dto) {
        // existing implementation
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // existing implementation
    }

    @Override
    public Company findEntityById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found: " + companyId));
    }

    @Override
    public Double getTotalSalaryForCompany(Long companyId) {
        Company company = findEntityById(companyId);

        // Use LAZY-loaded employees safely
        return company.getEmployees().stream()
                .mapToDouble(emp -> emp.getSalary() != null ? emp.getSalary() : 0)
                .sum();
    }
}
