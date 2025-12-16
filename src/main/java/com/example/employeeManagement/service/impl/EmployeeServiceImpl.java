package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.EmployeeDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.entity.EmployeeProduct;
import com.example.employeeManagement.entity.Product;
import com.example.employeeManagement.repository.EmployeeProductRepository;
import com.example.employeeManagement.repository.EmployeeRepository;
import com.example.employeeManagement.repository.ProductRepository;
import com.example.employeeManagement.service.CompanyService;
import com.example.employeeManagement.service.EmployeeService;
import com.example.employeeManagement.service.mapper.MappingHelpingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final EmployeeProductRepository employeeProductRepository;
    private final MappingHelpingService mappingHelpingService;
    private final CompanyService companyService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               ProductRepository productRepository,
                               EmployeeProductRepository employeeProductRepository,
                               MappingHelpingService mappingHelpingService,
                               CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.employeeProductRepository = employeeProductRepository;
        this.mappingHelpingService = mappingHelpingService;
        this.companyService = companyService;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO dto) {
        Employee employee = mappingHelpingService.convertToEntity(dto);

        if (dto.getCompanyId() != null) {
            Company company = companyService.findEntityById(dto.getCompanyId());
            employee.setCompany(company);
        } else {
            throw new RuntimeException("Company ID is required");
        }

        Employee saved = employeeRepository.save(employee);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(mappingHelpingService::convertToDTO)
                .toList();
    }

    @Override
    public Optional<EmployeeDTO> findById(Long id) {
        return employeeRepository.findById(id)
                .map(mappingHelpingService::convertToDTO);
    }

    @Override
    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (dto.getFirstName() != null) existing.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) existing.setLastName(dto.getLastName());
        if (dto.getEmail() != null) existing.setEmail(dto.getEmail());
        if (dto.getSalary() != null) existing.setSalary(dto.getSalary());
        if (dto.getPosition() != null) existing.setPosition(dto.getPosition());
        if (dto.getCompanyId() != null) {
            existing.setCompany(companyService.findEntityById(dto.getCompanyId()));
        }

        Employee saved = employeeRepository.save(existing);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void assignProductToEmployee(Long employeeId, Long productId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + employeeId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        EmployeeProduct employeeProduct = new EmployeeProduct();
        employeeProduct.setEmployee(employee);
        employeeProduct.setProduct(product);

        employeeProductRepository.save(employeeProduct);
    }
}
