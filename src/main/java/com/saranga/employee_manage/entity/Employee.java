package com.saranga.employee_manage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "employee_address")
    private String address;

    @Column(name = "employee_position")
    private String position;

    @Column(name = "employee_salary")
    private Double salary;
}
