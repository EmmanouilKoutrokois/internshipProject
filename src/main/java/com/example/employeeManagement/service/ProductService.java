package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.ProductDTO;
import com.example.employeeManagement.entity.Product;
import com.example.employeeManagement.repository.ProductRepository;
import com.example.employeeManagement.service.serviceMapper.Mapper;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final Mapper mapper;

    public ProductService(ProductRepository productRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public ProductDTO save(ProductDTO dto) {
        Product product = mapper.convertToEntity(dto);
        Product saved = productRepository.save(product);
        return mapper.convertToDTO(saved);
    }

    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(mapper::convertToDTO).toList();
    }

    public Optional<ProductDTO> findById(Long id) {
        return productRepository.findById(id).map(mapper::convertToDTO);
    }

    public ProductDTO update(Long id, ProductDTO dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        Product saved = productRepository.save(existing);
        return mapper.convertToDTO(saved);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
