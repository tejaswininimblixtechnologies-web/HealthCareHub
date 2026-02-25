//package nimblix.in.HealthCareHub.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class InsuranceProvider {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String policyType;
//    private String contactInfo;
//}

package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InsuranceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String contactInfo;

    @ElementCollection
    @CollectionTable(
            name = "insurance_provider_policy_types",
            joinColumns = @JoinColumn(name = "insurance_provider_id")
    )
    @Column(name = "policy_types")
    private List<String> policyTypes;
}