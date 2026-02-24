package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "role_permission_mapping")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    public RolePermission() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public Permission getPermission() { return permission; }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}