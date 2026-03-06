package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.request.ForgotPasswordRequest;
import nimblix.in.HealthCareHub.request.ResetPasswordRequest;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import nimblix.in.HealthCareHub.exception.UserNotFoundException;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository repository;

    @Autowired
    private UserRepository userRepository;

//    public List<Patient> getAllPatients() {
//
//        return repository.findByIsDeletedFalse();
//    }

    public String softDeletePatient(Long id) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

//        patient.setDeleted();   //  Mark as deleted
        repository.save(patient);

        return "Patient soft deleted successfully";
    }

    public Patient savePatient(Patient patient) {
        // TODO Auto-generated method stub
        return repository.save(patient);
    }

    @Override
    public MultipleImageResponse forgotPassword(ForgotPasswordRequest request) {

        if (request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());

        if (user == null) {
            throw new UserNotFoundException("User not found with this phone number");
        }

        MultipleImageResponse response = new MultipleImageResponse();
        response.setMessage("User verified. Please reset your password");

        return response;
    }

    @Override
    public MultipleImageResponse resetPassword(ResetPasswordRequest request) {

        if (request.getPhoneNumber() == null || request.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }

        if (request.getNewPassword() == null || request.getNewPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber());

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        user.setPassword(request.getNewPassword());
        userRepository.save(user);

        MultipleImageResponse response = new MultipleImageResponse();
        response.setMessage("Password reset successfully");

        return response;
    }

}
