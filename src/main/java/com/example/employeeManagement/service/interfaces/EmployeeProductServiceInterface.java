package com.example.employeeManagement.service.interfaces;

import com.example.employeeManagement.dto.EmployeeProductDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeProductServiceInterface {

    EmployeeProductDTO save(EmployeeProductDTO dto);

    List<EmployeeProductDTO> findAll();

    Optional<EmployeeProductDTO> findById(Long id);

    EmployeeProductDTO update(Long id, EmployeeProductDTO dto);

    void deleteById(Long id);
}
