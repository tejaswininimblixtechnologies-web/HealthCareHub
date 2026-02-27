package nimblix.in.HealthCareHub.response;

import lombok.Getter;
import lombok.Setter;
import nimblix.in.HealthCareHub.model.Patient;

@Getter
@Setter
public class PatientResponse {

    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String disease;
    private String bloodGroup;

    // Default constructor
    public PatientResponse() {}

    // Constructor from Patient
    public PatientResponse(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.age = patient.getAge();
        this.gender = patient.getGender();
        this.phone = patient.getPhone();
        this.disease = patient.getDisease();
        this.bloodGroup = patient.getBloodGroup();
    }
}