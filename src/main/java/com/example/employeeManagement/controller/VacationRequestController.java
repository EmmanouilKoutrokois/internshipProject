package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.VacationRequestDTO;
import com.example.employeeManagement.service.impl.VacationRequestServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacationRequests")
public class VacationRequestController {

    private final VacationRequestServiceImpl vacationRequestServiceImpl;

    public VacationRequestController(VacationRequestServiceImpl vacationRequestServiceImpl) {
        this.vacationRequestServiceImpl = vacationRequestServiceImpl;
    }

    // Existing CRUD endpoints ...

    // -------------------- NEW ENDPOINT --------------------
    @PatchMapping("/{vacationId}/respond")
    public ResponseEntity<VacationRequestDTO> respondToVacationRequest(
            @PathVariable Long vacationId,
            @RequestParam String status) {

        VacationRequestDTO updated = vacationRequestServiceImpl.respondToVacationRequest(vacationId, status);
        return ResponseEntity.ok(updated);
    }
}
