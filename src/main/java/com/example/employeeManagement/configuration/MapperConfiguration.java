package com.example.employeeManagement.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.employeeManagement.dto.BonusDTO;
import com.example.employeeManagement.entity.Bonus;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Explicitly map employeeId to a specific source
        modelMapper.typeMap(Bonus.class, BonusDTO.class).addMappings(mapper -> {
            mapper.map(bonus -> bonus.getEmployee() != null ? bonus.getEmployee().getId() : null, BonusDTO::setEmployeeId);
        });

        return modelMapper;
    }
}
