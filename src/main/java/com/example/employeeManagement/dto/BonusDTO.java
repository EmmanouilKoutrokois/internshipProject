package com.example.employeeManagement.dto;

import lombok.Data;

@Data
public class BonusDTO {
    private Long id;
    private Double amount;
    private String dateGranted;
    private Long employeeId;
}
