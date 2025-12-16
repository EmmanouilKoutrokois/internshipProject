package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.BonusDTO;
import com.example.employeeManagement.entity.Bonus;
import com.example.employeeManagement.service.impl.BonusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bonuses")
public class BonusController {

    private final BonusServiceImpl bonusService;

    // Constructor-based injection for BonusServiceImpl
    @Autowired
    public BonusController(BonusServiceImpl bonusService) {
        this.bonusService = bonusService;
    }

    // Create Bonus
    @PostMapping
    public ResponseEntity<BonusDTO> createBonus(@RequestBody BonusDTO dto) {
        BonusDTO saved = bonusService.save(dto);
        return ResponseEntity.ok(saved);
    }

    // Get All Bonuses
    @GetMapping
    public ResponseEntity<List<BonusDTO>> getAllBonuses() {
        List<BonusDTO> bonuses = bonusService.findAll();
        return ResponseEntity.ok(bonuses);
    }

    // Get Bonus By ID
    @GetMapping("/{id}")
    public ResponseEntity<BonusDTO> getBonusById(@PathVariable Long id) {
        return bonusService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Bonus
    @PutMapping("/{id}")
    public ResponseEntity<BonusDTO> updateBonus(@PathVariable Long id, @RequestBody BonusDTO dto) {
        BonusDTO updated = bonusService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete Bonus
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonus(@PathVariable Long id) {
        bonusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Create Bonuses for all Employees in a Company
    @PostMapping("/create")
    public ResponseEntity<List<Bonus>> createBonuses(
            @RequestParam Long companyId,
            @RequestParam String season) {
        List<Bonus> bonuses = bonusService.createBonusesForCompany(companyId, season);
        return ResponseEntity.ok(bonuses);
    }

    // Bonus Calculation
    @GetMapping("/calculate-bonus")
    public Double calculateBonus(@RequestParam Double salary, @RequestParam String season) {
        return bonusService.calculateBonus(salary, season);
    }
}
