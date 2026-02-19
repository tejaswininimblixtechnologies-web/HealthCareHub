package nimblix.in.HealthCareHub.helper;

import java.util.List;

public class MultipleImageResponse {

    private String status;
    private String message;
    private List<String> fileNames;

    public MultipleImageResponse() {
    }

    public MultipleImageResponse(String status, String message, List<String> fileNames) {
        this.status = status;
        this.message = message;
        this.fileNames = fileNames;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }
}

