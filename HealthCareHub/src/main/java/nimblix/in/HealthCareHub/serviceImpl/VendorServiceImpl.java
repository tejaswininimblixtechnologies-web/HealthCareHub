package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.VendorRequestDto;
import nimblix.in.HealthCareHub.model.Vendor;
import nimblix.in.HealthCareHub.repository.VendorRepository;
import nimblix.in.HealthCareHub.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    @Override
    public Vendor createVendor(VendorRequestDto requestDto) {

        if (vendorRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("Vendor already exists with this email");
        }

        Vendor vendor = new Vendor();
        vendor.setVendorName(requestDto.getVendorName());
        vendor.setEmail(requestDto.getEmail());
        vendor.setPhone(requestDto.getPhone());
        vendor.setAddress(requestDto.getAddress());
        vendor.setGstNumber(requestDto.getGstNumber());

        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
    }


}
