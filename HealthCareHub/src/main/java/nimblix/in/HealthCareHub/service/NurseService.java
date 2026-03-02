package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.NurseRequest;
import nimblix.in.HealthCareHub.response.NurseResponse;

import java.util.List;

public interface NurseService {
    
    String registerNurse(NurseRequest request);
    
    List<NurseResponse> getAllNurses(Long hospitalId, String department, String name);
    
    NurseResponse getNurseById(Long id);
}
