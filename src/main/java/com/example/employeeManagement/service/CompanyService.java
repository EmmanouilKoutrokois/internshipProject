package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyService(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    // Conversion Methods
    public Company convertToEntity(CompanyDTO dto) {
        return modelMapper.map(dto, Company.class);
    }

    public CompanyDTO convertToDTO(Company entity) {
        return modelMapper.map(entity, CompanyDTO.class);
    }

    // Create
    public CompanyDTO save(CompanyDTO dto) {
        Company company = convertToEntity(dto);
        Company saved = companyRepository.save(company);
        return convertToDTO(saved);
    }

    // Read All
    public List<CompanyDTO> findAll() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Read One
    public Optional<CompanyDTO> findById(Long id) {
        return companyRepository.findById(id).map(this::convertToDTO);
    }

    // Update
    public CompanyDTO update(Long id, CompanyDTO dto) {
        Company existing = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setEmail(dto.getEmail());
        Company saved = companyRepository.save(existing);
        return convertToDTO(saved);
    }

    // Delete
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
