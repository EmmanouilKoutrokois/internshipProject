package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.EmployeeProductDTO;
import com.example.employeeManagement.entity.EmployeeProduct;
import com.example.employeeManagement.repository.EmployeeProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeProductService {

    private final EmployeeProductRepository employeeProductRepository;
    private final ModelMapper modelMapper;

    public EmployeeProductService(EmployeeProductRepository employeeProductRepository, ModelMapper modelMapper) {
        this.employeeProductRepository = employeeProductRepository;
        this.modelMapper = modelMapper;
    }

    // Conversion Methods
    public EmployeeProduct convertToEntity(EmployeeProductDTO dto) {
        return modelMapper.map(dto, EmployeeProduct.class);
    }

    public EmployeeProductDTO convertToDTO(EmployeeProduct entity) {
        return modelMapper.map(entity, EmployeeProductDTO.class);
    }

    // Create
    public EmployeeProductDTO save(EmployeeProductDTO dto) {
        EmployeeProduct employeeProduct = convertToEntity(dto);
        EmployeeProduct saved = employeeProductRepository.save(employeeProduct);
        return convertToDTO(saved);
    }

    // Read All
    public List<EmployeeProductDTO> findAll() {
        List<EmployeeProduct> employeeProducts = employeeProductRepository.findAll();
        return employeeProducts.stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Read One
    public Optional<EmployeeProductDTO> findById(Long id) {
        return employeeProductRepository.findById(id).map(this::convertToDTO);
    }

    // Update
    public EmployeeProductDTO update(Long id, EmployeeProductDTO dto) {
        EmployeeProduct existing = employeeProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmployeeProduct not found"));
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setProductId(dto.getProductId());
        existing.setQuantity(dto.getQuantity());
        existing.setId(dto.getId());
        EmployeeProduct saved = employeeProductRepository.save(existing);
        return convertToDTO(saved);
    }

    // Delete
    public void deleteById(Long id) {
        employeeProductRepository.deleteById(id);
    }
}
