package com.example.employeeManagement.dto;

import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.enums.VacationStatus;
import lombok.Data;

@Data
public class VacationRequestDTO {
    private Long id;
    private String employeeFirstName;
    private String employeeLastName;
    private String startDate;
    private String endDate;
    private Integer vacationDays;
    private VacationStatus status;
    private Long employeeId;
    private Long companyId;

}
