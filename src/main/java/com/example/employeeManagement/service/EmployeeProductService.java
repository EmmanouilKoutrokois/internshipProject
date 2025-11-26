package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.EmployeeProductDTO;
import com.example.employeeManagement.entity.EmployeeProduct;
import com.example.employeeManagement.repository.EmployeeProductRepository;
import com.example.employeeManagement.service.serviceMapper.Mapper;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeProductService {

    private final EmployeeProductRepository employeeProductRepository;
    private final Mapper mapper;

    public EmployeeProductService(EmployeeProductRepository employeeProductRepository, Mapper mapper) {
        this.employeeProductRepository = employeeProductRepository;
        this.mapper = mapper;
    }

    public EmployeeProductDTO save(EmployeeProductDTO dto) {
        EmployeeProduct employeeProduct = mapper.convertToEntity(dto);
        EmployeeProduct saved = employeeProductRepository.save(employeeProduct);
        return mapper.convertToDTO(saved);
    }

    public List<EmployeeProductDTO> findAll() {
        List<EmployeeProduct> employeeProducts = employeeProductRepository.findAll();
        return employeeProducts.stream().map(mapper::convertToDTO).toList();
    }

    public Optional<EmployeeProductDTO> findById(Long id) {
        return employeeProductRepository.findById(id).map(mapper::convertToDTO);
    }

    public EmployeeProductDTO update(Long id, EmployeeProductDTO dto) {
        EmployeeProduct existing = employeeProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmployeeProduct not found"));
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setProductId(dto.getProductId());
        EmployeeProduct saved = employeeProductRepository.save(existing);
        return mapper.convertToDTO(saved);
    }

    public void deleteById(Long id) {
        employeeProductRepository.deleteById(id);
    }
}
