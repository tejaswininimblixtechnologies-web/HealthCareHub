package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}