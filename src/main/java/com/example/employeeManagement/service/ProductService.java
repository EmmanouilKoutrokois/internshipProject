package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.ProductDTO;
import com.example.employeeManagement.entity.Product;
import com.example.employeeManagement.repository.ProductRepository;
import com.example.employeeManagement.service.interfaces.ProductServiceInterface;
import com.example.employeeManagement.service.serviceMapper.Mapper;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements ProductServiceInterface {  // Implement the interface

    private final ProductRepository productRepository;
    private final Mapper mapper;

    public ProductService(ProductRepository productRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDTO save(ProductDTO dto) {  // Implement method from interface
        Product product = mapper.convertToEntity(dto);
        Product saved = productRepository.save(product);
        return mapper.convertToDTO(saved);
    }

    @Override
    public List<ProductDTO> findAll() {  // Implement method from interface
        List<Product> products = productRepository.findAll();
        return products.stream().map(mapper::convertToDTO).toList();
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {  // Implement method from interface
        return productRepository.findById(id).map(mapper::convertToDTO);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {  // Implement method from interface
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        Product saved = productRepository.save(existing);
        return mapper.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {  // Implement method from interface
        productRepository.deleteById(id);
    }
}
