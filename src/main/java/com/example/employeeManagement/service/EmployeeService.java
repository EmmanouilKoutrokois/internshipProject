package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.EmployeeDTO;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.repository.EmployeeRepository;
import com.example.employeeManagement.service.serviceMapper.Mapper;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Mapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, Mapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public EmployeeDTO save(EmployeeDTO dto) {
        Employee employee = mapper.convertToEntity(dto);
        Employee saved = employeeRepository.save(employee);
        return mapper.convertToDTO(saved);
    }

    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(mapper::convertToDTO).toList();
    }

    public Optional<EmployeeDTO> findById(Long id) {
        return employeeRepository.findById(id).map(mapper::convertToDTO);
    }

    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setId(dto.getId());
        existing.setSalary(dto.getSalary());
        Employee saved = employeeRepository.save(existing);
        return mapper.convertToDTO(saved);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
