package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.repository.CompanyRepository;
import com.example.employeeManagement.service.CompanyService;
import com.example.employeeManagement.service.mapper.MappingHelpingService;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {  // Implement the interface

    private final CompanyRepository companyRepository;
    private final MappingHelpingService mappingHelpingService;

    public CompanyServiceImpl(CompanyRepository companyRepository, MappingHelpingService mappingHelpingService) {
        this.companyRepository = companyRepository;
        this.mappingHelpingService = mappingHelpingService;
    }

    @Override
    public CompanyDTO save(CompanyDTO dto) {  // Implement method from interface
        Company company = mappingHelpingService.convertToEntity(dto);
        Company saved = companyRepository.save(company);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public List<CompanyDTO> findAll() {  // Implement method from interface
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(mappingHelpingService::convertToDTO).toList();
    }

    @Override
    public Optional<CompanyDTO> findById(Long id) {  // Implement method from interface
        return companyRepository.findById(id).map(mappingHelpingService::convertToDTO);
    }

    @Override
    public CompanyDTO update(Long id, CompanyDTO dto) {  // Implement method from interface
        Company existing = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        Company saved = companyRepository.save(existing);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {  // Implement method from interface
        companyRepository.deleteById(id);
    }
}
