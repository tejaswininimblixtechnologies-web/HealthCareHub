package nimblix.in.HealthCareHub.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadCommonService {
    
    List<String> uploadFiles(List<MultipartFile> files, String uploadPath) throws Exception;
    
    String uploadSingleFile(MultipartFile file, String uploadPath) throws Exception;
}
