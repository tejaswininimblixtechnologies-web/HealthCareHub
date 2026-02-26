package nimblix.in.HealthCareHub.response;

import lombok.Getter;
import lombok.Setter;
import nimblix.in.HealthCareHub.model.Patient;

@Getter
@Setter
public class PatientResponse {

    private Long id;
    private String name;
    private String age;
    private String gender;
    private String phone;
    private String disease;
    private String bloodGroup;

    // Constructor that takes a Patient object
    public PatientResponse(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.age = patient.getAge() != null ? patient.getAge().toString() : null;
        this.gender = patient.getGender();
        this.phone = patient.getPhone();
        this.disease = patient.getDisease();
        this.bloodGroup = patient.getBloodGroup();
    }
}