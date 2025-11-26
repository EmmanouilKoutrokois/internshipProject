package com.example.employeeManagement.service.interfaces;

import com.example.employeeManagement.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {

    ProductDTO save(ProductDTO dto);

    List<ProductDTO> findAll();

    Optional<ProductDTO> findById(Long id);

    ProductDTO update(Long id, ProductDTO dto);

    void deleteById(Long id);
}
