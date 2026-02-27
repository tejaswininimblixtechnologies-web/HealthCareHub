package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Vendor;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.VendorRepository;
import nimblix.in.HealthCareHub.request.VendorRequest;
import nimblix.in.HealthCareHub.response.VendorResponse;
import nimblix.in.HealthCareHub.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public VendorResponse createVendor(VendorRequest request) {

        if (vendorRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Vendor already exists with this email");
        }

        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Vendor vendor = new Vendor();
        vendor.setVendorName(request.getVendorName());
        vendor.setEmail(request.getEmail());
        vendor.setPhone(request.getPhone());
        vendor.setAddress(request.getAddress());
        vendor.setGstNumber(request.getGstNumber());
        vendor.setHospital(hospital);

        vendor = vendorRepository.save(vendor);

        return mapToResponse(vendor);
    }

    @Override
    public List<VendorResponse> getAllVendors() {

        List<Vendor> vendors = vendorRepository.findAll();

        return vendors.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VendorResponse getVendorById(Long id) {

        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        return mapToResponse(vendor);
    }

    private VendorResponse mapToResponse(Vendor vendor) {

        return new VendorResponse(
                vendor.getVendorId(),
                vendor.getVendorName(),
                vendor.getEmail(),
                vendor.getPhone(),
                vendor.getAddress(),
                vendor.getGstNumber()
        );
    }
}