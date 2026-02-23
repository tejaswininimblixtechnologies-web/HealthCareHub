package nimblix.in.HealthCareHub.dto;

public class HospitalDropdownDTO {

    private Long id;
    private String name;

    public HospitalDropdownDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}