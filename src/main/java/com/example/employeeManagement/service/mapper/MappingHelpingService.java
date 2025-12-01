package com.example.employeeManagement.service.mapper;

import com.example.employeeManagement.dto.*;
import com.example.employeeManagement.entity.*;
import com.example.employeeManagement.enums.BonusRate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MappingHelpingService {

    private final ModelMapper modelMapper;

    public MappingHelpingService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // BONUS MAPPINGS
    public Bonus convertToEntity(BonusDTO dto) {
        Bonus bonus = modelMapper.map(dto, Bonus.class);

        // Ensure employee relationship is set for persistence
        if (dto.getEmployeeId() != null) {
            Employee emp = new Employee();
            emp.setId(dto.getEmployeeId());
            bonus.setEmployee(emp); // Set the employee object in the bonus entity
        }

        return bonus;
    }

    public BonusDTO convertToDTO(Bonus entity) {
        BonusDTO dto = modelMapper.map(entity, BonusDTO.class);

        // Handle employeeId manually in case ModelMapper doesn't map it correctly
        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getId());
        }

        return dto;
    }

    // COMPANY MAPPINGS
    public Company convertToEntity(CompanyDTO dto) { return modelMapper.map(dto, Company.class); }
    public CompanyDTO convertToDTO(Company entity) { return modelMapper.map(entity, CompanyDTO.class); }

    // EMPLOYEE MAPPINGS
    public Employee convertToEntity(EmployeeDTO dto) { return modelMapper.map(dto, Employee.class); }
    public EmployeeDTO convertToDTO(Employee entity) { return modelMapper.map(entity, EmployeeDTO.class); }

    // EMPLOYEE_PRODUCT MAPPINGS
    public EmployeeProduct convertToEntity(EmployeeProductDTO dto) { return modelMapper.map(dto, EmployeeProduct.class); }
    public EmployeeProductDTO convertToDTO(EmployeeProduct entity) { return modelMapper.map(entity, EmployeeProductDTO.class); }

    // PRODUCT MAPPINGS
    public Product convertToEntity(ProductDTO dto) { return modelMapper.map(dto, Product.class); }
    public ProductDTO convertToDTO(Product entity) { return modelMapper.map(entity, ProductDTO.class); }

    // VACATION_REQUEST MAPPINGS
    public VacationRequest convertToEntity(VacationRequestDTO dto) { return modelMapper.map(dto, VacationRequest.class); }
    public VacationRequestDTO convertToDTO(VacationRequest entity) { return modelMapper.map(entity, VacationRequestDTO.class); }

    // BONUS RATE ENUM MAPPING
    public Double mapBonusRateToPercentage(String season) {
        return BonusRate.getRateBySeason(season);
    }
}
