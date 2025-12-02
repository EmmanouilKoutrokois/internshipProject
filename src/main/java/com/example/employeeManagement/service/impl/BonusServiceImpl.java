package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.BonusDTO;
import com.example.employeeManagement.entity.Bonus;
import com.example.employeeManagement.entity.Company;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.enums.BonusRate;
import com.example.employeeManagement.repository.BonusRepository;
import com.example.employeeManagement.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BonusServiceImpl {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private static BonusRepository bonusRepository;

    @Autowired
    private static CompanyRepository companyRepository;

    // Convert BonusDTO to Bonus entity
    public Bonus convertToEntity(BonusDTO dto) {
        Bonus bonus = modelMapper.map(dto, Bonus.class);

        if (dto.getEmployeeId() != null) {
            Employee emp = new Employee();
            emp.setId(dto.getEmployeeId());
            bonus.setEmployee(emp);
        }
        return bonus;
    }

    // Convert Bonus entity to BonusDTO
    public BonusDTO convertToDTO(Bonus entity) {
        BonusDTO dto = modelMapper.map(entity, BonusDTO.class);
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

    public void deleteById(Long id) {}

    public Double calculateBonus(Double salary, String season) {
        return null;
    }

    public static List<Bonus> createBonusesForCompany(Long companyId, String season) {

        // 1. Get company
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found: " + companyId));

        // 2. Fetch employees via LAZY list
        List<Employee> employees = company.getEmployees();

        // 3. Determine bonus rate based on season
        Double rate = BonusRate.getRateBySeason(season);

        // 4. Prepare Bonus objects
        List<Bonus> bonuses = new ArrayList<>();

        for (Employee employee : employees) {
            Bonus bonus = new Bonus();
            bonus.setEmployee(employee);
            bonus.setDateGranted(LocalDate.now());
            bonus.setAmount(employee.getSalary() * rate);

            bonuses.add(bonus);
        }

        // 5. Mandatory saveAll
        return bonusRepository.saveAll(bonuses);
    }
}
