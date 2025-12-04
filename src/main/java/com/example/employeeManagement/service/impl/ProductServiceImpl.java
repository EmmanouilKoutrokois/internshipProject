package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.ProductDTO;
import com.example.employeeManagement.entity.Product;
import com.example.employeeManagement.repository.ProductRepository;
import com.example.employeeManagement.service.ProductService;
import com.example.employeeManagement.service.mapper.MappingHelpingService;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {  // Implement the interface

    private final ProductRepository productRepository;
    private final MappingHelpingService mappingHelpingService;

    public ProductServiceImpl(ProductRepository productRepository, MappingHelpingService mappingHelpingService) {
        this.productRepository = productRepository;
        this.mappingHelpingService = mappingHelpingService;
    }

    @Override
    public ProductDTO save(ProductDTO dto) {  // Implement method from interface
        Product product = mappingHelpingService.convertToEntity(dto);
        Product saved = productRepository.save(product);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public List<ProductDTO> findAll() {  // Implement method from interface
        List<Product> products = productRepository.findAll();
        return products.stream().map(mappingHelpingService::convertToDTO).toList();
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {  // Implement method from interface
        return productRepository.findById(id).map(mappingHelpingService::convertToDTO);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {  // Implement method from interface
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        Product saved = productRepository.save(existing);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {  // Implement method from interface
        productRepository.deleteById(id);
    }
}