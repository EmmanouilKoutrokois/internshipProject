package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.service.CompanyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyServiceImpl companyServiceImpl;

    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyServiceImpl = companyServiceImpl;
    }

    @GetMapping
    public List<CompanyDTO> getAllCompanies() {
        return companyServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CompanyDTO> getCompanyById(@PathVariable Long id) {
        return companyServiceImpl.findById(id);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyDTO savedCompany = companyServiceImpl.save(companyDTO);  // Service method expects a CompanyDTO
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);  // Return the saved DTO
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyServiceImpl.deleteById(id);
    }
}
