package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.VacationRequestDTO;
import com.example.employeeManagement.entity.VacationRequest;
import com.example.employeeManagement.repository.VacationRequestRepository;
import com.example.employeeManagement.service.interfaces.VacationRequestServiceInterface;
import com.example.employeeManagement.service.serviceMapper.Mapper;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VacationRequestService implements VacationRequestServiceInterface {  // Implement the interface

    private final VacationRequestRepository vacationRequestRepository;
    private final Mapper mapper;

    public VacationRequestService(VacationRequestRepository vacationRequestRepository, Mapper mapper) {
        this.vacationRequestRepository = vacationRequestRepository;
        this.mapper = mapper;
    }

    @Override
    public VacationRequestDTO save(VacationRequestDTO dto) {  // Implement method from interface
        VacationRequest vacationRequest = mapper.convertToEntity(dto);
        VacationRequest saved = vacationRequestRepository.save(vacationRequest);
        return mapper.convertToDTO(saved);
    }

    @Override
    public List<VacationRequestDTO> findAll() {  // Implement method from interface
        List<VacationRequest> vacationRequests = vacationRequestRepository.findAll();
        return vacationRequests.stream().map(mapper::convertToDTO).toList();
    }

    @Override
    public Optional<VacationRequestDTO> findById(Long id) {  // Implement method from interface
        return vacationRequestRepository.findById(id).map(mapper::convertToDTO);
    }

    @Override
    public VacationRequestDTO update(Long id, VacationRequestDTO dto) {  // Implement method from interface
        VacationRequest existing = vacationRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VacationRequest not found"));
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        VacationRequest saved = vacationRequestRepository.save(existing);
        return mapper.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {  // Implement method from interface
        vacationRequestRepository.deleteById(id);
    }
}
