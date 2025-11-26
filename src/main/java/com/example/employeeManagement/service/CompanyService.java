package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.repository.CompanyRepository;
import com.example.employeeManagement.service.serviceMapper.Mapper;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final Mapper mapper;

    public CompanyService(CompanyRepository companyRepository, Mapper mapper) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    public CompanyDTO save(CompanyDTO dto) {
        Company company = mapper.convertToEntity(dto);
        Company saved = companyRepository.save(company);
        return mapper.convertToDTO(saved);
    }

    public List<CompanyDTO> findAll() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(mapper::convertToDTO).toList();
    }

    public Optional<CompanyDTO> findById(Long id) {
        return companyRepository.findById(id).map(mapper::convertToDTO);
    }

    public CompanyDTO update(Long id, CompanyDTO dto) {
        Company existing = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        Company saved = companyRepository.save(existing);
        return mapper.convertToDTO(saved);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
