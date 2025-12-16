package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.BonusDTO;
import com.example.employeeManagement.entity.Bonus;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.enums.BonusRate;
import com.example.employeeManagement.repository.BonusRepository;
import com.example.employeeManagement.repository.CompanyRepository;
import com.example.employeeManagement.service.BonusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BonusServiceImpl implements BonusService {

    private final BonusRepository bonusRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    // Constructor-based injection for dependencies
    @Autowired
    public BonusServiceImpl(BonusRepository bonusRepository, CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.bonusRepository = bonusRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BonusDTO save(BonusDTO dto) {
        Bonus bonus = convertToEntity(dto);
        Bonus savedBonus = bonusRepository.save(bonus);
        return convertToDTO(savedBonus);
    }

    private Bonus convertToEntity(BonusDTO dto) {
        return null;
    }

    @Override
    public List<BonusDTO> findAll() {
        List<Bonus> bonuses = bonusRepository.findAll();
        return bonuses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BonusDTO> findById(Long id) {
        return bonusRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public BonusDTO update(Long id, BonusDTO dto) {
        Bonus bonus = bonusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bonus not found"));
        bonus.setAmount(dto.getAmount());
        bonus.setDateGranted(dto.getDateGranted());
        Bonus updatedBonus = bonusRepository.save(bonus);
        return convertToDTO(updatedBonus);
    }

    @Override
    public void deleteById(Long id) {
        bonusRepository.deleteById(id);
    }

    @Override
    public Double calculateBonus(Double salary, String season) {
        Double rate = BonusRate.getRateBySeason(season);
        return salary * rate;
    }

    @Override
    public List<Bonus> createBonusesForCompany(Long companyId, String season) {
        // Retrieve the company by its ID
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found: " + companyId));

        // Retrieve employees, ensuring it's not null
        List<Employee> employees = company.getEmployees();
        if (employees == null || employees.isEmpty()) {
            throw new RuntimeException("No employees found for company ID: " + companyId);
        }

        // Get the bonus rate for the given season
        Double rate;
        try {
            rate = BonusRate.getRateBySeason(season);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid season: " + season);
        }

        List<Bonus> bonuses = new ArrayList<>();
        for (Employee employee : employees) {
            // Ensure the employee's salary is not null
            if (employee.getSalary() == null) {
                throw new RuntimeException("Employee with ID " + employee.getId() + " has no salary set.");
            }

            Bonus bonus = new Bonus();
            bonus.setEmployee(employee);
            bonus.setDateGranted(LocalDate.now());
            bonus.setAmount(employee.getSalary() * rate); // Calculate bonus for each employee

            bonuses.add(bonus);
        }

        return bonusRepository.saveAll(bonuses); // Save all bonuses at once
    }


    // Convert Bonus entity to BonusDTO
    private BonusDTO convertToDTO(Bonus entity) {
        BonusDTO dto = modelMapper.map(entity, BonusDTO.class);
        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getId());
        }
        return dto;
    }
}
