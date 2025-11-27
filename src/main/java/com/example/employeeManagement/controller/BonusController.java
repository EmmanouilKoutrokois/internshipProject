package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.BonusDTO;
import com.example.employeeManagement.service.impl.BonusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bonuses")
public class BonusController {

    @Autowired
    private final BonusServiceImpl bonusService;

    public BonusController(BonusServiceImpl bonusService) {
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
