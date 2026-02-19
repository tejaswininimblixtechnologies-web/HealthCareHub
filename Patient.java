package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String disease;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
