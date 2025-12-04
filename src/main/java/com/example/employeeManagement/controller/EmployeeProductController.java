package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.EmployeeProductDTO;
import com.example.employeeManagement.service.impl.EmployeeProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employeeProducts")
public class EmployeeProductController {

    private final EmployeeProductServiceImpl employeeProductServiceImpl;

    public EmployeeProductController(EmployeeProductServiceImpl employeeProductServiceImpl) {
        this.employeeProductServiceImpl = employeeProductServiceImpl;
    }

    // Create
    @PostMapping
    public ResponseEntity<EmployeeProductDTO> createEmployeeProduct(@RequestBody EmployeeProductDTO dto) {
        EmployeeProductDTO saved = employeeProductServiceImpl.save(dto);
        return ResponseEntity.ok(saved);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<EmployeeProductDTO>> getAllEmployeeProducts() {
        List<EmployeeProductDTO> employeeProducts = employeeProductServiceImpl.findAll();
        return ResponseEntity.ok(employeeProducts);
    }

    // Read One
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProductDTO> getEmployeeProductById(@PathVariable Long id) {
        return employeeProductServiceImpl.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProductDTO> updateEmployeeProduct(@PathVariable Long id, @RequestBody EmployeeProductDTO dto) {
        EmployeeProductDTO updated = employeeProductServiceImpl.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeProduct(@PathVariable Long id) {
        employeeProductServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}