package com.example.employeeManagement.dto;

import lombok.Data;

@Data
public class EmployeeProductDTO {
    private Long id;
    private Long employeeId;
    private Long productId;
    private Integer quantity;
}