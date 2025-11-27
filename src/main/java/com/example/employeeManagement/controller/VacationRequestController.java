package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.VacationRequestDTO;
import com.example.employeeManagement.service.impl.VacationRequestServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vacationRequests")
public class VacationRequestController {

    private final VacationRequestServiceImpl vacationRequestServiceImpl;

    public VacationRequestController(VacationRequestServiceImpl vacationRequestServiceImpl) {
        this.vacationRequestServiceImpl = vacationRequestServiceImpl;
    }

    // Create
    @PostMapping
    public ResponseEntity<VacationRequestDTO> createVacationRequest(@RequestBody VacationRequestDTO dto) {
        VacationRequestDTO saved = vacationRequestServiceImpl.save(dto);
        return ResponseEntity.ok(saved);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<VacationRequestDTO>> getAllVacationRequests() {
        List<VacationRequestDTO> requests = vacationRequestServiceImpl.findAll();
        return ResponseEntity.ok(requests);
    }

    // Read One
    @GetMapping("/{id}")
    public ResponseEntity<VacationRequestDTO> getVacationRequestById(@PathVariable Long id) {
        return vacationRequestServiceImpl.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<VacationRequestDTO> updateVacationRequest(@PathVariable Long id, @RequestBody VacationRequestDTO dto) {
        VacationRequestDTO updated = vacationRequestServiceImpl.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacationRequest(@PathVariable Long id) {
        vacationRequestServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
