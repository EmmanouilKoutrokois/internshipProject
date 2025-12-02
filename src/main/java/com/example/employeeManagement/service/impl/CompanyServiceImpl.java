package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.entity.EmployeeProduct;
import com.example.employeeManagement.entity.Product;
import com.example.employeeManagement.repository.CompanyRepository;
import com.example.employeeManagement.repository.EmployeeRepository;
import com.example.employeeManagement.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CompanyDTO save(CompanyDTO dto) {
        return null;
    }

    @Override
    public List<CompanyDTO> findAll() {
        return null;
    }

    @Override
    public Optional<CompanyDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public CompanyDTO update(Long id, CompanyDTO dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
    }

    @Override
    public Company findEntityById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found: " + companyId));
    }

    @Override
    public Double getTotalSalaryForCompany(Long companyId) {
        Company company = findEntityById(companyId);

        return company.getEmployees().stream()
                .mapToDouble(emp -> emp.getSalary() != null ? emp.getSalary() : 0)
                .sum();
    }

    @Override
    public Map<String, List<Product>> getEmployeeProducts(Long companyId) {
        Company company = findEntityById(companyId);

        return company.getEmployees().stream()
                .filter(emp -> emp.getEmployeeProducts() != null && !emp.getEmployeeProducts().isEmpty())
                .collect(Collectors.toMap(
                        emp -> emp.getFirstName() + " " + emp.getLastName(),
                        emp -> emp.getEmployeeProducts().stream()
                                .map(EmployeeProduct::getProduct)
                                .collect(Collectors.toList())
                ));
    }
}
