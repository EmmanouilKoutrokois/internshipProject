package com.example.employeeManagement.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private Double totalSalary;
}