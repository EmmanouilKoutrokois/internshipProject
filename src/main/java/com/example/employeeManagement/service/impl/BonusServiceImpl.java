package com.example.employeeManagement.service.impl;

import com.example.employeeManagement.dto.BonusDTO;
import com.example.employeeManagement.entity.Bonus;
import com.example.employeeManagement.enums.BonusRate;
import com.example.employeeManagement.repository.BonusRepository;
import com.example.employeeManagement.service.BonusService;
import com.example.employeeManagement.service.mapper.MappingHelpingService;  // Import Mapper from the same package
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BonusServiceImpl implements BonusService {  // Implement the interface

    private final BonusRepository bonusRepository;
    private final MappingHelpingService mappingHelpingService;  // Inject Mapper

    public BonusServiceImpl(BonusRepository bonusRepository, MappingHelpingService mappingHelpingService) {
        this.bonusRepository = bonusRepository;
        this.mappingHelpingService = mappingHelpingService;  // Initialize Mapper
    }

    @Override
    public BonusDTO save(BonusDTO dto) {  // Implement method from interface
        Bonus bonus = mappingHelpingService.convertToEntity(dto);  // Use Mapper to convert DTO to Entity
        Bonus saved = bonusRepository.save(bonus);
        return mappingHelpingService.convertToDTO(saved);  // Convert saved entity back to DTO
    }

    @Override
    public List<BonusDTO> findAll() {  // Implement method from interface
        List<Bonus> bonuses = bonusRepository.findAll();
        return bonuses.stream()
                .map(mappingHelpingService::convertToDTO)  // Use Mapper for each entity to DTO conversion
                .toList();
    }

    @Override
    public Optional<BonusDTO> findById(Long id) {  // Implement method from interface
        return bonusRepository.findById(id).map(mappingHelpingService::convertToDTO);  // Use Mapper for conversion
    }

    @Override
    public BonusDTO update(Long id, BonusDTO dto) {  // Implement method from interface
        Bonus existing = bonusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bonus not found"));
        existing.setAmount(dto.getAmount());
        existing.setDateGranted(LocalDate.parse(dto.getDateGranted()));
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setId(dto.getId());
        Bonus saved = bonusRepository.save(existing);
        return mappingHelpingService.convertToDTO(saved);  // Use Mapper to convert the updated entity to DTO
    }

    @Override
    public void deleteById(Long id) {  // Implement method from interface
        bonusRepository.deleteById(id);
    }

    @Override
    public Double calculateBonus(Double salary, String season) {  // Implement custom method from interface
        // Get the rate from the enum using the season
        Double rate = BonusRate.getRateBySeason(season);
        // Calculate and return the bonus
        return salary * rate;
    }
}
