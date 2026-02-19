package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Branch {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String location;

        @OneToMany(mappedBy = "branch")
        private List<Doctor> doctors;

        @OneToMany(mappedBy = "branch")
        private List<Patient> patients;

        @OneToMany(mappedBy = "branch")
        private List<Inventory> inventoryList;
}