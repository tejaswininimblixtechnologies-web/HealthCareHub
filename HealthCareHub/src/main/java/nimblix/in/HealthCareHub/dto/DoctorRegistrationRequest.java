package nimblix.in.HealthCareHub.dto;

public class DoctorRegistrationRequest {

    private String name;
    private String mobileNumber;
    private String specialization;
    private String availableDate;

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }
}