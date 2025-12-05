package com.example.employeeManagement.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.employeeManagement.dto.VacationRequestDTO;
import com.example.employeeManagement.entity.VacationRequest;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Lambda-based mapping for VacationRequest -> VacationRequestDTO
        modelMapper.typeMap(VacationRequest.class, VacationRequestDTO.class).addMappings(mapper -> {
            // Map Employee ID safely
            mapper.map(src -> src.getEmployee().getId(), VacationRequestDTO::setEmployeeId);

            // Map Employee first name
            mapper.map(src -> src.getEmployee().getFirstName(), VacationRequestDTO::setEmployeeFirstName);

            // Map Employee last name
            mapper.map(src -> src.getEmployee().getLastName(), VacationRequestDTO::setEmployeeLastName);

            // Map Company ID safely
            mapper.map(src -> src.getCompany().getId(), VacationRequestDTO::setCompanyId);
        });

        return modelMapper;
    }
}
