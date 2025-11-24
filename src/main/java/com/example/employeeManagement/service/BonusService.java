package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.BonusDTO;
import com.example.employeeManagement.entity.Bonus;
import com.example.employeeManagement.repository.BonusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
@Transactional
public class BonusService {

    private final BonusRepository bonusRepository;
    private final ModelMapper modelMapper;

    public BonusService(BonusRepository bonusRepository, ModelMapper modelMapper) {
        this.bonusRepository = bonusRepository;
        this.modelMapper = modelMapper;
    }

    // Conversion Methods
    public Bonus convertToEntity(BonusDTO dto) {
        return modelMapper.map(dto, Bonus.class);
    }

    public BonusDTO convertToDTO(Bonus entity) {
        return modelMapper.map(entity, BonusDTO.class);
    }

    // Create
    public BonusDTO save(BonusDTO dto) {
        Bonus bonus = convertToEntity(dto);
        Bonus saved = bonusRepository.save(bonus);
        return convertToDTO(saved);
    }

    // Read All
    public List<BonusDTO> findAll() {
        List<Bonus> bonuses = bonusRepository.findAll();
        return bonuses.stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Read One
    public Optional<BonusDTO> findById(Long id) {
        return bonusRepository.findById(id).map(this::convertToDTO);
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
        return convertToDTO(saved);
    }

    // Delete
    public void deleteById(Long id) {
        bonusRepository.deleteById(id);
    }
}
