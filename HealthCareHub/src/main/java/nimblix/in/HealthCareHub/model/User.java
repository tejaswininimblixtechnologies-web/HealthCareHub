package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;
import nimblix.in.HealthCareHub.utility.HealthCareUtil;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    //  KEEP ENUM (IMPORTANT)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;

    //  REQUIRED FOR SPRING SECURITY
    @Column(nullable = false)
    private boolean enabled = true;

    //  ROLE PERMISSIONS STORED IN SEPARATE TABLE
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_permissions",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "permission", nullable = false)
    private Set<String> permissions = new HashSet<>();

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;

    @PrePersist
    protected void onCreate() {
        String currentTime =
                HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        this.createdTime = currentTime;
        this.updatedTime = currentTime;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime =
                HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }
}
