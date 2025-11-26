package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.EmployeeDTO;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.repository.EmployeeRepository;
import com.example.employeeManagement.service.interfaces.EmployeeServiceInterface;
import com.example.employeeManagement.service.serviceMapper.Mapper;  // Import the Mapper class
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeServiceInterface {  // Implement the interface

    private final EmployeeRepository employeeRepository;
    private final Mapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, Mapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO dto) {  // Implement method from interface
        Employee employee = mapper.convertToEntity(dto);
        Employee saved = employeeRepository.save(employee);
        return mapper.convertToDTO(saved);
    }

    @Override
    public List<EmployeeDTO> findAll() {  // Implement method from interface
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(mapper::convertToDTO).toList();
    }

    @Override
    public Optional<EmployeeDTO> findById(Long id) {  // Implement method from interface
        return employeeRepository.findById(id).map(mapper::convertToDTO);
    }

    @Override
    public EmployeeDTO update(Long id, EmployeeDTO dto) {  // Implement method from interface
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setId(dto.getId());
        existing.setSalary(dto.getSalary());
        Employee saved = employeeRepository.save(existing);
        return mapper.convertToDTO(saved);
    }

    @Override
    public void deleteById(Long id) {  // Implement method from interface
        employeeRepository.deleteById(id);
    }
}
