package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class VendorRequest {

    private String vendorName;
    private String email;
    private String phone;
    private String address;
    private String gstNumber;
    private Long hospitalId;
}