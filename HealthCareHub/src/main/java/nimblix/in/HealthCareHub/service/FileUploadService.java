package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {
    MultipleImageResponse uploadFiles(List<MultipartFile> files) throws Exception;
}
