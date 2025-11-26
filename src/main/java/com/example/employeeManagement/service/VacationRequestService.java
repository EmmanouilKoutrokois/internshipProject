package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.VacationRequestDTO;
import com.example.employeeManagement.entity.VacationRequest;
import com.example.employeeManagement.repository.VacationRequestRepository;
import com.example.employeeManagement.service.serviceMapper.Mapper;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;
    private final Mapper mapper;

    public VacationRequestService(VacationRequestRepository vacationRequestRepository, Mapper mapper) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.mapper = mapper;
    }

    public VacationRequestDTO save(VacationRequestDTO dto) {
        VacationRequest vacationRequest = mapper.convertToEntity(dto);
        VacationRequest saved = vacationRequestRepository.save(vacationRequest);
        return mapper.convertToDTO(saved);
    }

    public List<VacationRequestDTO> findAll() {
        List<VacationRequest> vacationRequests = vacationRequestRepository.findAll();
        return vacationRequests.stream().map(mapper::convertToDTO).toList();
    }

    public Optional<VacationRequestDTO> findById(Long id) {
        return vacationRequestRepository.findById(id).map(mapper::convertToDTO);
    }

    public VacationRequestDTO update(Long id, VacationRequestDTO dto) {
        VacationRequest existing = vacationRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VacationRequest not found"));
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        VacationRequest saved = vacationRequestRepository.save(existing);
        return mapper.convertToDTO(saved);
    }

    public void deleteById(Long id) {
        vacationRequestRepository.deleteById(id);
    }
}
