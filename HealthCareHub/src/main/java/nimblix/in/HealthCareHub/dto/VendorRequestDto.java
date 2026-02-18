package nimblix.in.HealthCareHub.dto;

import lombok.Data;

@Data
public class VendorRequestDto {

    private String vendorName;
    private String email;
    private String phone;
    private String address;
    private String gstNumber;
}