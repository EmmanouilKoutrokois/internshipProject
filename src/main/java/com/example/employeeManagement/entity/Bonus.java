package com.example.employeeManagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bonus")
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private LocalDate dateGranted;

    private Long employeeId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
