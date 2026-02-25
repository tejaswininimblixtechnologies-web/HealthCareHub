package nimblix.in.HealthCareHub.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultipleImageResponse {

    private String status;
    private String message;
    private List<String> fileNames;
}
