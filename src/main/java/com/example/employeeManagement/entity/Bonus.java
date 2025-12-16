package com.example.employeeManagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "bonus")
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false, precision = 10)
    private Double amount;

    @Column(name = "date_granted")
    private LocalDate dateGranted;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bonus bonus = (Bonus) o;
        return Objects.equals(id, bonus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setDateGranted(String dateGranted) {
    }

    public void setDateGranted(LocalDate now) {
    }
}
