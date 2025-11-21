package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.ProductDTO;
import com.example.employeeManagement.entity.Product;
import com.example.employeeManagement.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    // Conversion Methods
    public Product convertToEntity(ProductDTO dto) {
        return modelMapper.map(dto, Product.class);
    }

    public ProductDTO convertToDTO(Product entity) {
        return modelMapper.map(entity, ProductDTO.class);
    }

    // Create
    public ProductDTO save(ProductDTO dto) {
        Product product = convertToEntity(dto);
        Product saved = productRepository.save(product);
        return convertToDTO(saved);
    }

    // Read All
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Read One
    public Optional<ProductDTO> findById(Long id) {
        return productRepository.findById(id).map(this::convertToDTO);
    }

    // Update
    public ProductDTO update(Long id, ProductDTO dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setId(dto.getId());
        Product saved = productRepository.save(existing);
        return convertToDTO(saved);
    }

    // Delete
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
