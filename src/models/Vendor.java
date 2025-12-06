package models;

public class Vendor {
    private int idVendor;
    private String nama;
    private String namaPemilik;
    private String alamat;
    private String noTelp;
    private double harga;
    private int idJenisVendor;

    public Vendor() {}

    public Vendor(int idVendor, String nama, String namaPemilik, String alamat,
                  String noTelp, double harga, int idJenisVendor) {
        this.idVendor = idVendor;
        this.nama = nama;
        this.namaPemilik = namaPemilik;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.harga = harga;
        this.idJenisVendor = idJenisVendor;
    }

    public int getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(int idVendor) {
        this.idVendor = idVendor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getIdJenisVendor() {
        return idJenisVendor;
    }

    public void setIdJenisVendor(int idJenisVendor) {
        this.idJenisVendor = idJenisVendor;
    }
}
