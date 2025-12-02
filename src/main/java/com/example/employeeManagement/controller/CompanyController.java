package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.entity.Bonus;
import com.example.employeeManagement.service.impl.BonusServiceImpl;
import com.example.employeeManagement.service.impl.CompanyServiceImpl;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Getter
    private final BonusServiceImpl bonusServiceImpl;
    private final CompanyServiceImpl companyServiceImpl;

    public CompanyController(CompanyServiceImpl companyServiceImpl) {
        this.companyServiceImpl = companyServiceImpl;
        bonusServiceImpl = null;
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
        CompanyDTO savedCompany = companyServiceImpl.save(companyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyServiceImpl.deleteById(id);
    }

    // ⭐ NEW ENDPOINT
    @GetMapping("/{companyId}/total-salary")
    public Double getTotalSalary(@PathVariable Long companyId) {
        return companyServiceImpl.getTotalSalaryForCompany(companyId);
    }
    
    @PostMapping("/{companyId}/bonuses")
    public List<Bonus> createCompanyBonuses(
            @PathVariable Long companyId,
            @RequestParam String season) {

        return BonusServiceImpl.createBonusesForCompany(companyId, season);
    }
}
