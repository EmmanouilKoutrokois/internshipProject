package com.example.employeeManagement.service.mapper;

import com.example.employeeManagement.dto.BonusDTO;
import com.example.employeeManagement.dto.CompanyDTO;
import com.example.employeeManagement.dto.EmployeeDTO;
import com.example.employeeManagement.dto.EmployeeProductDTO;
import com.example.employeeManagement.dto.ProductDTO;
import com.example.employeeManagement.dto.VacationRequestDTO;
import com.example.employeeManagement.entity.Bonus;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.entity.EmployeeProduct;
import com.example.employeeManagement.entity.Product;
import com.example.employeeManagement.entity.VacationRequest;
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
        return modelMapper.map(dto, Bonus.class);
    }

    public BonusDTO convertToDTO(Bonus entity) {
        return modelMapper.map(entity, BonusDTO.class);
    }

    // COMPANY MAPPINGS
    public Company convertToEntity(CompanyDTO dto) {
        return modelMapper.map(dto, Company.class);
    }

    public CompanyDTO convertToDTO(Company entity) {
        return modelMapper.map(entity, CompanyDTO.class);
    }

    // EMPLOYEE MAPPINGS
    public Employee convertToEntity(EmployeeDTO dto) {
        return modelMapper.map(dto, Employee.class);
    }

    public EmployeeDTO convertToDTO(Employee entity) {
        return modelMapper.map(entity, EmployeeDTO.class);
    }

    // EMPLOYEE_PRODUCT MAPPINGS
    public EmployeeProduct convertToEntity(EmployeeProductDTO dto) {
        return modelMapper.map(dto, EmployeeProduct.class);
    }

    public EmployeeProductDTO convertToDTO(EmployeeProduct entity) {
        return modelMapper.map(entity, EmployeeProductDTO.class);
    }

    // PRODUCT MAPPINGS
    public Product convertToEntity(ProductDTO dto) {
        return modelMapper.map(dto, Product.class);
    }

    public ProductDTO convertToDTO(Product entity) {
        return modelMapper.map(entity, ProductDTO.class);
    }

    // VACATION_REQUEST MAPPINGS
    public VacationRequest convertToEntity(VacationRequestDTO dto) {
        return modelMapper.map(dto, VacationRequest.class);
    }

    public VacationRequestDTO convertToDTO(VacationRequest entity) {
        return modelMapper.map(entity, VacationRequestDTO.class);
    }

    // BONUS RATE ENUM MAPPING (Custom Mapping)
    public Double mapBonusRateToPercentage(String season) {
        // Assuming BonusRate enum has a method getRateBySeason()
        return BonusRate.getRateBySeason(season);
    }
}
