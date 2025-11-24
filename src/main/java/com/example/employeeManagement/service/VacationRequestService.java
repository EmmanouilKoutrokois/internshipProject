package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.VacationRequestDTO;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.entity.VacationRequest;
import com.example.employeeManagement.enums.VacationStatus;
import com.example.employeeManagement.repository.EmployeeRepository;
import com.example.employeeManagement.repository.VacationRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VacationRequestService {

    private final VacationRequestRepository vacationRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    // Constructor injection for dependencies
    @Autowired
    public VacationRequestService(VacationRequestRepository vacationRequestRepository,
                                  EmployeeRepository employeeRepository,
                                  ModelMapper modelMapper) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.employeeRepository = employeeRepository;
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
        // Fetch the employee data to check remaining vacation days
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Calculate number of vacation days requested
        long requestedDays = java.time.temporal.ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());

        // Check if the employee has enough vacation days
        if (requestedDays > employee.getVacationDays()) {
            throw new RuntimeException("Employee does not have enough vacation days.");
        }

        // Set the status of the vacation request to "PENDING"
        dto.setStatus(VacationStatus.PENDING.name());

        // Convert DTO to entity and save
        VacationRequest vacationRequest = convertToEntity(dto);
        VacationRequest savedRequest = vacationRequestRepository.save(vacationRequest);

        // Return the saved VacationRequest as a DTO
        return convertToDTO(savedRequest);
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
