package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class TreatmentPlanRequest {

    private String treatmentPlan;


    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public Long getAppointmentId() {
        return null;
    }
}
