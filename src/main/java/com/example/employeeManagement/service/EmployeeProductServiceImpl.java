package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.EmployeeProductDTO;
import com.example.employeeManagement.entity.EmployeeProduct;
import com.example.employeeManagement.repository.EmployeeProductRepository;
import com.example.employeeManagement.service.interfaces.EmployeeProductServiceInterface;
import com.example.employeeManagement.service.serviceMapper.Mapper;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeProductServiceImpl implements EmployeeProductServiceInterface {  // Implement the interface

    private final EmployeeProductRepository employeeProductRepository;
    private final Mapper mapper;

    public EmployeeProductServiceImpl(EmployeeProductRepository employeeProductRepository, Mapper mapper) {
        this.employeeProductRepository = employeeProductRepository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeProductDTO save(EmployeeProductDTO dto) {  // Implement method from interface
        EmployeeProduct employeeProduct = mapper.convertToEntity(dto);
        EmployeeProduct saved = employeeProductRepository.save(employeeProduct);
        return mapper.convertToDTO(saved);
    }

    @Override
    public List<EmployeeProductDTO> findAll() {  // Implement method from interface
        List<EmployeeProduct> employeeProducts = employeeProductRepository.findAll();
        return employeeProducts.stream().map(mapper::convertToDTO).toList();
    }

    @Override
    public Optional<EmployeeProductDTO> findById(Long id) {  // Implement method from interface
        return employeeProductRepository.findById(id).map(mapper::convertToDTO);
    }

    @Override
    public EmployeeProductDTO update(Long id, EmployeeProductDTO dto) {  // Implement method from interface
        EmployeeProduct existing = employeeProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmployeeProduct not found"));
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setProductId(dto.getProductId());
        EmployeeProduct saved = employeeProductRepository.save(existing);
        return mapper.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {  // Implement method from interface
        employeeProductRepository.deleteById(id);
    }
}
