package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.VendorRequestDto;
import nimblix.in.HealthCareHub.model.Vendor;

import java.util.List;

public interface VendorService {

    Vendor createVendor(VendorRequestDto requestDto);

    List<Vendor> getAllVendors();

    Vendor getVendorById(Long id);

}