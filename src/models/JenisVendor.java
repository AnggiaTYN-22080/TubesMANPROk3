package models;

public class JenisVendor {
    private int idJenisVendor;
    private String nama;

    public JenisVendor() {}

    public JenisVendor(int idJenisVendor, String nama) {
        this.idJenisVendor = idJenisVendor;
        this.nama = nama;
    }

    public int getIdJenisVendor() {
        return idJenisVendor;
    }

    public void setIdJenisVendor(int idJenisVendor) {
        this.idJenisVendor = idJenisVendor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
