package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.EmployeeProductDTO;
import com.example.employeeManagement.entity.EmployeeProduct;
import com.example.employeeManagement.repository.EmployeeProductRepository;
import com.example.employeeManagement.service.EmployeeProductService;
import com.example.employeeManagement.service.mapper.MappingHelpingService;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeProductServiceImpl implements EmployeeProductService {  // Implement the interface

    private final EmployeeProductRepository employeeProductRepository;
    private final MappingHelpingService mappingHelpingService;

    public EmployeeProductServiceImpl(EmployeeProductRepository employeeProductRepository, MappingHelpingService mappingHelpingService) {
        this.employeeProductRepository = employeeProductRepository;
        this.mappingHelpingService = mappingHelpingService;
    }

    @Override
    public EmployeeProductDTO save(EmployeeProductDTO dto) {  // Implement method from interface
        EmployeeProduct employeeProduct = mappingHelpingService.convertToEntity(dto);
        EmployeeProduct saved = employeeProductRepository.save(employeeProduct);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public List<EmployeeProductDTO> findAll() {  // Implement method from interface
        List<EmployeeProduct> employeeProducts = employeeProductRepository.findAll();
        return employeeProducts.stream().map(mappingHelpingService::convertToDTO).toList();
    }

    @Override
    public Optional<EmployeeProductDTO> findById(Long id) {  // Implement method from interface
        return employeeProductRepository.findById(id).map(mappingHelpingService::convertToDTO);
    }

    @Override
    public EmployeeProductDTO update(Long id, EmployeeProductDTO dto) {  // Implement method from interface
        EmployeeProduct existing = employeeProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmployeeProduct not found"));
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setProductId(dto.getProductId());
        EmployeeProduct saved = employeeProductRepository.save(existing);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {  // Implement method from interface
        employeeProductRepository.deleteById(id);
    }
}