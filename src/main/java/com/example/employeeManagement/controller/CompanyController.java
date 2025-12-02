package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.service.CompanyService;
import com.example.employeeManagement.service.impl.BonusServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final BonusServiceImpl bonusServiceImpl;

    @GetMapping
    public List<CompanyDTO> getAllCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CompanyDTO> getCompanyById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyDTO savedCompany = companyService.save(companyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteById(id);
    }

    // ✅ Total salary endpoint returning CompanyDTO with totalSalary
    @GetMapping("/{companyId}/total-salary")
    public ResponseEntity<CompanyDTO> getTotalSalary(@PathVariable Long companyId) {

        Company company = companyService.findEntityById(companyId);

        Double totalSalary = companyService.getTotalSalaryForCompany(companyId);

        CompanyDTO dto = new CompanyDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setAddress(company.getAddress());
        dto.setPhoneNumber(company.getPhoneNumber());
        dto.setEmail(company.getEmail());
        dto.setTotalSalary(totalSalary);

        return ResponseEntity.ok(dto);
    }

    // Example for creating bonuses — call via injected BonusServiceImpl
    @PostMapping("/{companyId}/bonuses")
    public ResponseEntity<?> createCompanyBonuses(
            @PathVariable Long companyId,
            @RequestParam String season) {

        var bonuses = BonusServiceImpl.createBonusesForCompany(companyId, season);
        return ResponseEntity.ok(bonuses);
    }
}
