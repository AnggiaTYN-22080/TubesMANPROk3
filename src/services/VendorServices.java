package services;

import models.JenisVendor;
import models.Vendor;
import repositories.JenisVendorRepository;
import repositories.VendorRepository;

import java.util.List;

public class VendorServices {
    private final VendorRepository vendorRepo = new VendorRepository();
    private final JenisVendorRepository jenisRepo = new JenisVendorRepository();


    public List<JenisVendor> getAllJenisVendor() {
        return jenisRepo.findAll();
    }
    public boolean addJenisVendor(String nama) {
        return jenisRepo.insert(nama);
    }
}