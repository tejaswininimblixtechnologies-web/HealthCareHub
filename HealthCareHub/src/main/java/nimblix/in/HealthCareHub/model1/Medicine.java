package nimblix.in.HealthCareHub.model1;

import jakarta.persistence.*;
import lombok.*;

    @Entity
    @Table(name = "medicines")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor

    public class Medicine {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;

        private String name;

        private String company;

        private double price;




    }


