package com.example.employeeManagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String position;

    private Double salary;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    // One employee can have many bonuses
    @OneToMany(mappedBy = "employee")
    private List<Bonus> bonuses;

    // One employee can have many vacation requests
    @OneToMany(mappedBy = "employee")
    private List<VacationRequest> vacationRequests;

    // One employee can have many EmployeeProduct entries
    @OneToMany(mappedBy = "employee")
    private List<EmployeeProduct> employeeProducts;
}
