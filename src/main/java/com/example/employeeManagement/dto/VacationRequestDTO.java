package com.example.employeeManagement.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class VacationRequestDTO {
    private Long id;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
