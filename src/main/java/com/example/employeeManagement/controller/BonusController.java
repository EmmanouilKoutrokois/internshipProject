package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.BonusDTO;
import com.example.employeeManagement.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bonuses")
public class BonusController {

    @Autowired
    private final BonusService bonusService;

    public BonusController(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    // Create
    @PostMapping
    public ResponseEntity<BonusDTO> createBonus(@RequestBody BonusDTO dto) {
        BonusDTO saved = bonusService.save(dto);
        return ResponseEntity.ok(saved);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<BonusDTO>> getAllBonuses() {
        List<BonusDTO> bonuses = bonusService.findAll();
        return ResponseEntity.ok(bonuses);
    }

    // Read One
    @GetMapping("/{id}")
    public ResponseEntity<BonusDTO> getBonusById(@PathVariable Long id) {
        return bonusService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<BonusDTO> updateBonus(@PathVariable Long id, @RequestBody BonusDTO dto) {
        BonusDTO updated = bonusService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonus(@PathVariable Long id) {
        bonusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/calculate-bonus")
    public Double calculateBonus(@RequestParam Double salary, @RequestParam String season) {
        return bonusService.calculateBonus(salary, season);
    }
}
