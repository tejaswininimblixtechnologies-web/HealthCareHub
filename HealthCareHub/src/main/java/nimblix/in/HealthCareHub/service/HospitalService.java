package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Hospital;
import java.util.List;

public interface HospitalService {

    List<Hospital> searchAndFilter(
            String name,
            String city,
            String state,
            Integer minBeds);
}