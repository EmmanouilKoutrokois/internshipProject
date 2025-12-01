package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.BonusDTO;
import com.example.employeeManagement.entity.Bonus;
import com.example.employeeManagement.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonusServiceImpl {

    @Autowired
    private ModelMapper modelMapper;

    // Convert BonusDTO to Bonus entity
    public Bonus convertToEntity(BonusDTO dto) {
        Bonus bonus = modelMapper.map(dto, Bonus.class);

        // Ensure the employee relationship is set for persistence
        if (dto.getEmployeeId() != null) {
            Employee emp = new Employee();
            emp.setId(dto.getEmployeeId());
            bonus.setEmployee(emp);  // Set the employee object in the bonus entity
        }

        return bonus;
    }

    // Convert Bonus entity to BonusDTO
    public BonusDTO convertToDTO(Bonus entity) {
        BonusDTO dto = modelMapper.map(entity, BonusDTO.class);

        // Handle employeeId manually in case ModelMapper doesn't map it correctly
        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getId());
        }

        return dto;
    }

    public List<BonusDTO> findAll() {
        return List.of();
    }

    public BonusDTO save(BonusDTO dto) {
        return null;
    }

    public BonusDTO findById(Long id) {
        return null;
    }

    public BonusDTO update(Long id, BonusDTO dto) {
        return null;
    }

    public void deleteById(Long id) {
    }

    public Double calculateBonus(Double salary, String season) {
        return null;
    }
}
