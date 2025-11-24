package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.VacationRequestDTO;
import com.example.employeeManagement.entity.VacationRequest;
import com.example.employeeManagement.repository.VacationRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class VacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;
    private final ModelMapper modelMapper;

    public VacationRequestService(VacationRequestRepository vacationRequestRepository, ModelMapper modelMapper) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.modelMapper = modelMapper;
    }

    // Conversion Methods
    public VacationRequest convertToEntity(VacationRequestDTO dto) {
        return modelMapper.map(dto, VacationRequest.class);
    }

    public VacationRequestDTO convertToDTO(VacationRequest entity) {
        return modelMapper.map(entity, VacationRequestDTO.class);
    }

    // Create
    public VacationRequestDTO save(VacationRequestDTO dto) {
        VacationRequest vacationRequest = convertToEntity(dto);
        VacationRequest saved = vacationRequestRepository.save(vacationRequest);
        return convertToDTO(saved);
    }

    // Read All
    public List<VacationRequestDTO> findAll() {
        List<VacationRequest> requests = vacationRequestRepository.findAll();
        return requests.stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Read One
    public Optional<VacationRequestDTO> findById(Long id) {
        return vacationRequestRepository.findById(id).map(this::convertToDTO);
    }

    // Update
    public VacationRequestDTO update(Long id, VacationRequestDTO dto) {
        VacationRequest existing = vacationRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VacationRequest not found"));
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        existing.setStatus(dto.getStatus());
        existing.setId(dto.getId());
        VacationRequest saved = vacationRequestRepository.save(existing);
        return convertToDTO(saved);
    }

    // Delete
    public void deleteById(Long id) {
        vacationRequestRepository.deleteById(id);
    }
}
