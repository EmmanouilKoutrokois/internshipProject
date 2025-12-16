package com.example.employeeManagement.entity;

import com.example.employeeManagement.enums.VacationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vacationrequest")  // Ensure this table name matches your database exactly.
public class VacationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private Integer vacationDays;

    @Enumerated(EnumType.STRING)
    private VacationStatus status;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    // Helper setters for DTO conversion
    public void setEmployeeId(Long employeeId) {
        if (this.employee == null) this.employee = new Employee();
        this.employee.setId(employeeId);
    }

    public void setCompanyId(Long companyId) {
        if (this.company == null) this.company = new Company();
        this.company.setId(companyId);
    }
}
