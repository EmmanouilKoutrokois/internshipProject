package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyDTO> getAllCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CompanyDTO> getCompanyById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return companyService.save(company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteById(id);
    }
}
