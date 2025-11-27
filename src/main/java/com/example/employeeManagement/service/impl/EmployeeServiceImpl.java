package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.EmployeeDTO;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.repository.EmployeeRepository;
import com.example.employeeManagement.service.EmployeeService;
import com.example.employeeManagement.service.mapper.MappingHelpingService;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {  // Implement the interface

    private final EmployeeRepository employeeRepository;
    private final MappingHelpingService mappingHelpingService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, MappingHelpingService mappingHelpingService) {
        this.employeeRepository = employeeRepository;
        this.mappingHelpingService = mappingHelpingService;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO dto) {  // Implement method from interface
        Employee employee = mappingHelpingService.convertToEntity(dto);
        Employee saved = employeeRepository.save(employee);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public List<EmployeeDTO> findAll() {  // Implement method from interface
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(mappingHelpingService::convertToDTO).toList();
    }

    @Override
    public Optional<EmployeeDTO> findById(Long id) {  // Implement method from interface
        return employeeRepository.findById(id).map(mappingHelpingService::convertToDTO);
    }

    @Override
    public EmployeeDTO update(Long id, EmployeeDTO dto) {  // Implement method from interface
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setId(dto.getId());
        existing.setSalary(dto.getSalary());
        Employee saved = employeeRepository.save(existing);
        return mappingHelpingService.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {  // Implement method from interface
        employeeRepository.deleteById(id);
    }
}
