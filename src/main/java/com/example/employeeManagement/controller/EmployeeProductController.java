package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.EmployeeProductDTO;
import com.example.employeeManagement.service.EmployeeProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeeProducts")
public class EmployeeProductController {

    private final EmployeeProductService employeeProductService;

    public EmployeeProductController(EmployeeProductService employeeProductService) {
        this.employeeProductService = employeeProductService;
    }

    // Create
    @PostMapping
    public ResponseEntity<EmployeeProductDTO> createEmployeeProduct(@RequestBody EmployeeProductDTO dto) {
        EmployeeProductDTO saved = employeeProductService.save(dto);
        return ResponseEntity.ok(saved);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<EmployeeProductDTO>> getAllEmployeeProducts() {
        List<EmployeeProductDTO> employeeProducts = employeeProductService.findAll();
        return ResponseEntity.ok(employeeProducts);
    }

    // Read One
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProductDTO> getEmployeeProductById(@PathVariable Long id) {
        return employeeProductService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProductDTO> updateEmployeeProduct(@PathVariable Long id, @RequestBody EmployeeProductDTO dto) {
        EmployeeProductDTO updated = employeeProductService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeProduct(@PathVariable Long id) {
        employeeProductService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
