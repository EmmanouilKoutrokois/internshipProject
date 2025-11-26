package com.example.employeeManagement.service.interfaces;

import com.example.employeeManagement.dto.CompanyDTO;

import java.util.List;
import java.util.Optional;

public interface CompanyServiceInterface {

    CompanyDTO save(CompanyDTO dto);

    List<CompanyDTO> findAll();

    Optional<CompanyDTO> findById(Long id);

    CompanyDTO update(Long id, CompanyDTO dto);

    void deleteById(Long id);
}
