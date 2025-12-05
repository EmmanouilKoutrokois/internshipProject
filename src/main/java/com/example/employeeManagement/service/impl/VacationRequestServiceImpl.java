package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.VacationRequestDTO;
import com.example.employeeManagement.entity.VacationRequest;
import com.example.employeeManagement.enums.VacationStatus;
import com.example.employeeManagement.repository.VacationRequestRepository;
import com.example.employeeManagement.service.VacationRequestService;
import com.example.employeeManagement.service.mapper.MappingHelpingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VacationRequestServiceImpl implements VacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;
    private final MappingHelpingService mappingHelpingService;

    public VacationRequestServiceImpl(VacationRequestRepository vacationRequestRepository,
                                      MappingHelpingService mappingHelpingService) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.mappingHelpingService = mappingHelpingService;
    }

    @Override
    public VacationRequestDTO save(VacationRequestDTO dto) {
        VacationRequest vacationRequest = mappingHelpingService.convertToEntity(dto);
        VacationRequest saved = vacationRequestRepository.save(vacationRequest);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public List<VacationRequestDTO> findAll() {
        return vacationRequestRepository.findAll().stream()
                .map(mappingHelpingService::convertToDTO)
                .toList();
    }

    @Override
    public Optional<VacationRequestDTO> findById(Long id) {
        return vacationRequestRepository.findById(id)
                .map(mappingHelpingService::convertToDTO);
    }

    @Override
    public VacationRequestDTO update(Long id, VacationRequestDTO dto) {
        VacationRequest existing = vacationRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VacationRequest not found"));

        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        existing.setVacationDays(dto.getVacationDays());

        if (dto.getStatus() != null) {
            existing.setStatus(VacationStatus.valueOf(dto.getStatus().trim().toUpperCase()));
        }
        if (dto.getEmployeeId() != null) {
            existing.getEmployee().setId(dto.getEmployeeId());
        }
        if (dto.getCompanyId() != null) {
            existing.getCompany().setId(dto.getCompanyId());
        }

        VacationRequest saved = vacationRequestRepository.save(existing);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {
        vacationRequestRepository.deleteById(id);
    }
}
