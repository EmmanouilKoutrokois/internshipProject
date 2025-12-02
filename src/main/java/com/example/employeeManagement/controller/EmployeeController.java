package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.EmployeeDTO;
import com.example.employeeManagement.service.impl.EmployeeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(employeeServiceImpl.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeServiceImpl.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id,
                                                      @RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(employeeServiceImpl.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
