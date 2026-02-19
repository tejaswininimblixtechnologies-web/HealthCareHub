package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}

