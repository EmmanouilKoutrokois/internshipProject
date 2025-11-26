package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.BonusDTO;
import com.example.employeeManagement.entity.Bonus;
import com.example.employeeManagement.enums.BonusRate;
import com.example.employeeManagement.repository.BonusRepository;
import com.example.employeeManagement.service.serviceMapper.Mapper;  // Import Mapper from the same package
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BonusService {

    private final BonusRepository bonusRepository;
    private final Mapper mapper;  // Inject Mapper

    public BonusService(BonusRepository bonusRepository, Mapper mapper) {
        this.bonusRepository = bonusRepository;
        this.mapper = mapper;  // Initialize Mapper
    }

    // Create
    public BonusDTO save(BonusDTO dto) {
        Bonus bonus = mapper.convertToEntity(dto);  // Use Mapper to convert DTO to Entity
        Bonus saved = bonusRepository.save(bonus);
        return mapper.convertToDTO(saved);  // Convert saved entity back to DTO
    }

    // Read All
    public List<BonusDTO> findAll() {
        List<Bonus> bonuses = bonusRepository.findAll();
        return bonuses.stream()
                .map(mapper::convertToDTO)  // Use Mapper for each entity to DTO conversion
                .toList();
    }

    // Read One
    public Optional<BonusDTO> findById(Long id) {
        return bonusRepository.findById(id).map(mapper::convertToDTO);  // Use Mapper for conversion
    }

    // Update
    public BonusDTO update(Long id, BonusDTO dto) {
        Bonus existing = bonusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bonus not found"));
        existing.setAmount(dto.getAmount());
        existing.setDateGranted(LocalDate.parse(dto.getDateGranted()));
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setId(dto.getId());
        Bonus saved = bonusRepository.save(existing);
        return mapper.convertToDTO(saved);  // Use Mapper to convert the updated entity to DTO
    }

    // Delete
    public void deleteById(Long id) {
        bonusRepository.deleteById(id);
    }

    // Custom method to calculate the bonus
    public Double calculateBonus(Double salary, String season) {
        // Get the rate from the enum using the season
        Double rate = BonusRate.getRateBySeason(season);
        // Calculate and return the bonus
        return salary * rate;
    }
}
