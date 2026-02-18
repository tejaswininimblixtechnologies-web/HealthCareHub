package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.helper.UploadImageHelper;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/nurse")
@RequiredArgsConstructor
public class NurseController {

    private final UploadImageHelper uploadImageHelper;

    @PostMapping("/upload")
    public MultipleImageResponse uploadFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
        return uploadImageHelper.uploadImages(files);
    }
}
