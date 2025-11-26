package com.example.employeeManagement.service.interfaces;

import com.example.employeeManagement.dto.VacationRequestDTO;

import java.util.List;
import java.util.Optional;

public interface VacationRequestServiceInterface {

    VacationRequestDTO save(VacationRequestDTO dto);

    List<VacationRequestDTO> findAll();

    Optional<VacationRequestDTO> findById(Long id);

    VacationRequestDTO update(Long id, VacationRequestDTO dto);

    void deleteById(Long id);
}
