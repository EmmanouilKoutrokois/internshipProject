package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.repository.CompanyRepository;
import com.example.employeeManagement.service.interfaces.CompanyServiceInterface;
import com.example.employeeManagement.service.serviceMapper.Mapper;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService implements CompanyServiceInterface {  // Implement the interface

    private final CompanyRepository companyRepository;
    private final Mapper mapper;

    public CompanyService(CompanyRepository companyRepository, Mapper mapper) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    @Override
    public CompanyDTO save(CompanyDTO dto) {  // Implement method from interface
        Company company = mapper.convertToEntity(dto);
        Company saved = companyRepository.save(company);
        return mapper.convertToDTO(saved);
    }

    @Override
    public List<CompanyDTO> findAll() {  // Implement method from interface
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(mapper::convertToDTO).toList();
    }

    @Override
    public Optional<CompanyDTO> findById(Long id) {  // Implement method from interface
        return companyRepository.findById(id).map(mapper::convertToDTO);
    }

    @Override
    public CompanyDTO update(Long id, CompanyDTO dto) {  // Implement method from interface
        Company existing = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        Company saved = companyRepository.save(existing);
        return mapper.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {  // Implement method from interface
        companyRepository.deleteById(id);
    }
}
