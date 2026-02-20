package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.helper.UploadImageHelper;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")   // Only one RequestMapping
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final UploadImageHelper uploadImageHelper;

    // Doctor Registration API
    @PostMapping("/register")
    public String registerDoctor(@RequestBody DoctorRegistrationRequest doctorRegistrationRequest) {
        return doctorService.registerDoctor(doctorRegistrationRequest); 
    }

    // Multiple File Upload API
    @PostMapping("/upload")
    public MultipleImageResponse uploadFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
        return uploadImageHelper.uploadImages(files);
    }
}
