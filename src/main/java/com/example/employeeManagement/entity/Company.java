package com.example.employeeManagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;
    
    private String  phoneNumber;

    private String email;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;
}
