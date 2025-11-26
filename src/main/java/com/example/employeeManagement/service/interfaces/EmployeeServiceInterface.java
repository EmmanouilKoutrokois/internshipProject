package com.example.employeeManagement.service.interfaces;

import com.example.employeeManagement.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceInterface {

    EmployeeDTO save(EmployeeDTO dto);

    List<EmployeeDTO> findAll();

    Optional<EmployeeDTO> findById(Long id);

    EmployeeDTO update(Long id, EmployeeDTO dto);

    void deleteById(Long id);
}
