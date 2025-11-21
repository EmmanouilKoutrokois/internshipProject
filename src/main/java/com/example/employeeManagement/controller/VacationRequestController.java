package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.VacationRequestDTO;
import com.example.employeeManagement.service.VacationRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacationRequests")
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;

    public VacationRequestController(VacationRequestService vacationRequestService) {
        this.vacationRequestService = vacationRequestService;
    }

    // Create
    @PostMapping
    public ResponseEntity<VacationRequestDTO> createVacationRequest(@RequestBody VacationRequestDTO dto) {
        VacationRequestDTO saved = vacationRequestService.save(dto);
        return ResponseEntity.ok(saved);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<VacationRequestDTO>> getAllVacationRequests() {
        List<VacationRequestDTO> requests = vacationRequestService.findAll();
        return ResponseEntity.ok(requests);
    }

    // Read One
    @GetMapping("/{id}")
    public ResponseEntity<VacationRequestDTO> getVacationRequestById(@PathVariable Long id) {
        return vacationRequestService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<VacationRequestDTO> updateVacationRequest(@PathVariable Long id, @RequestBody VacationRequestDTO dto) {
        VacationRequestDTO updated = vacationRequestService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacationRequest(@PathVariable Long id) {
        vacationRequestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
