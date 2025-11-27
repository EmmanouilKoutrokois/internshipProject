package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.VacationRequestDTO;
import com.example.employeeManagement.entity.VacationRequest;
import com.example.employeeManagement.repository.VacationRequestRepository;
import com.example.employeeManagement.service.VacationRequestService;
import com.example.employeeManagement.service.mapper.MappingHelpingService;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VacationRequestServiceImpl implements VacationRequestService {  // Implement the interface

    private final VacationRequestRepository vacationRequestRepository;
    private final MappingHelpingService mappingHelpingService;

    public VacationRequestServiceImpl(VacationRequestRepository vacationRequestRepository, MappingHelpingService mappingHelpingService) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.mappingHelpingService = mappingHelpingService;
    }

    @Override
    public VacationRequestDTO save(VacationRequestDTO dto) {  // Implement method from interface
        VacationRequest vacationRequest = mappingHelpingService.convertToEntity(dto);
        VacationRequest saved = vacationRequestRepository.save(vacationRequest);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public List<VacationRequestDTO> findAll() {  // Implement method from interface
        List<VacationRequest> vacationRequests = vacationRequestRepository.findAll();
        return vacationRequests.stream().map(mappingHelpingService::convertToDTO).toList();
    }

    @Override
    public Optional<VacationRequestDTO> findById(Long id) {  // Implement method from interface
        return vacationRequestRepository.findById(id).map(mappingHelpingService::convertToDTO);
    }

    @Override
    public VacationRequestDTO update(Long id, VacationRequestDTO dto) {  // Implement method from interface
        VacationRequest existing = vacationRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VacationRequest not found"));
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        VacationRequest saved = vacationRequestRepository.save(existing);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {  // Implement method from interface
        vacationRequestRepository.deleteById(id);
    }
}
