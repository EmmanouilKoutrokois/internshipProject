package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.BonusDTO;

import java.util.List;
import java.util.Optional;

public interface BonusService {

    BonusDTO save(BonusDTO dto);

    List<BonusDTO> findAll();

    Optional<BonusDTO> findById(Long id);

    BonusDTO update(Long id, BonusDTO dto);

    void deleteById(Long id);

    Double calculateBonus(Double salary, String season);
}
