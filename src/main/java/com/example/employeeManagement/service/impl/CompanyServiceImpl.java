package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.repository.CompanyRepository;
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

    // -------------------- DTO MAPPING --------------------

    private CompanyDTO toDTO(Company entity) {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());

        // If you want to include salary in ALL responses:
        if (entity.getEmployees() != null) {
            double totalSalary = entity.getEmployees().stream()
                    .mapToDouble(emp -> emp.getSalary() == null ? 0 : emp.getSalary())
                    .sum();
            dto.setTotalSalary(totalSalary);
        }

        return dto;
    }

    private Company toEntity(CompanyDTO dto) {
        Company company = new Company();
        company.setId(dto.getId());
        company.setName(dto.getName());
        company.setAddress(dto.getAddress());
        company.setPhoneNumber(dto.getPhoneNumber());
        company.setEmail(dto.getEmail());
        return company;
    }

    // -------------------- SERVICE METHODS --------------------

    @Override
    public CompanyDTO save(CompanyDTO dto) {
        Company entity = toEntity(dto);
        Company saved = companyRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    public List<CompanyDTO> findAll() {
        return companyRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CompanyDTO> findById(Long id) {
        return companyRepository.findById(id)
                .map(this::toDTO);
    }

    @Override
    public CompanyDTO update(Long id, CompanyDTO dto) {
        Company existing = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found: " + id));

        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setEmail(dto.getEmail());

        Company updated = companyRepository.save(existing);
        return toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found: " + id);
        }
        companyRepository.deleteById(id);
    }

    @Override
    public Company findEntityById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found: " + id));
    }

    @Override
    public Double getTotalSalaryForCompany(Long companyId) {
        Company company = findEntityById(companyId);

        return company.getEmployees().stream()
                .mapToDouble(emp -> emp.getSalary() != null ? emp.getSalary() : 0)
                .sum();
    }

    @Override
    public Map<String, List<com.example.employeeManagement.entity.Product>> getEmployeeProducts(Long companyId) {
        Company company = findEntityById(companyId);

        return company.getEmployees().stream()
                .filter(emp -> emp.getEmployeeProducts() != null && !emp.getEmployeeProducts().isEmpty())
                .collect(Collectors.toMap(
                        emp -> emp.getFirstName() + " " + emp.getLastName(),
                        emp -> emp.getEmployeeProducts()
                                .stream()
                                .map(ep -> ep.getProduct())
                                .collect(Collectors.toList())
                ));
    }
}
