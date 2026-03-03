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

        // 1️⃣ Null request check
        if (request == null) {
            throw new IllegalArgumentException("Vendor request cannot be null");
        }

        // 2️⃣ Mandatory field validation
        if (request.getVendorName() == null || request.getVendorName().trim().isEmpty()) {
            throw new IllegalArgumentException("Vendor name is required");
        }

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (request.getHospitalId() == null) {
            throw new IllegalArgumentException("Hospital ID is required");
        }

        // 3️⃣ Email format validation
        if (!request.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // 4️⃣ Check duplicate email
        if (vendorRepository.existsByEmail(request.getEmail().trim().toLowerCase())) {
            throw new RuntimeException("Vendor already exists with this email");
        }

        // 5️⃣ Hospital existence check
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found with ID: " + request.getHospitalId()));

        // 6️⃣ Phone validation (optional but safe)
        if (request.getPhone() != null && !request.getPhone().matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be 10 digits");
        }

        // 7️⃣ GST validation (basic format check)
        if (request.getGstNumber() != null && !request.getGstNumber().isEmpty()) {
            if (!request.getGstNumber().matches("^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[A-Z0-9]{1}[Z]{1}[A-Z0-9]{1}$")) {
                throw new IllegalArgumentException("Invalid GST number format");
            }
        }

        // 8️⃣ Trim & normalize
        Vendor vendor = new Vendor();
        vendor.setVendorName(request.getVendorName().trim());
        vendor.setEmail(request.getEmail().trim().toLowerCase());
        vendor.setPhone(request.getPhone() != null ? request.getPhone().trim() : null);
        vendor.setAddress(request.getAddress() != null ? request.getAddress().trim() : null);
        vendor.setGstNumber(request.getGstNumber() != null ? request.getGstNumber().trim() : null);
        vendor.setHospital(hospital);

        vendor = vendorRepository.save(vendor);

        return mapToResponse(vendor);
    }

    @Override
    public List<VendorResponse> getAllVendors() {

        List<Vendor> vendors = vendorRepository.findAll();

        if (vendors.isEmpty()) {
            throw new RuntimeException("No vendors found");
        }

        return vendors.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VendorResponse getVendorById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid vendor ID");
        }

        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + id));

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