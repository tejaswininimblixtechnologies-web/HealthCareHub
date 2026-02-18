package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String gender;


    }

