package services;

import models.JenisVendor;
import models.Vendor;
import repositories.JenisVendorRepository;
import repositories.VendorRepository;

import java.util.List;

public class VendorService {

    private final VendorRepository vendorRepo = new VendorRepository();
    private final JenisVendorRepository jenisRepo = new JenisVendorRepository();

    public List<Vendor> getAllVendor() {
        return vendorRepo.findAll();
    }

    public Vendor getVendorById(int id) {
        return vendorRepo.findById(id);
    }

    public boolean addVendor(String nama, String pemilik, String alamat,
                             String noTelp, double harga, int idJenisVendor) {
        Vendor v = new Vendor(0, nama, pemilik, alamat, noTelp, harga, idJenisVendor);
        return vendorRepo.insert(v);
    }

    public boolean updateVendor(Vendor v) {
        return vendorRepo.update(v);
    }

    public boolean deleteVendor(int idVendor) {
        return vendorRepo.delete(idVendor);
    }

    public JenisVendor getJenisVendorById(int id) {
        return jenisRepo.findById(id);
    }
}
