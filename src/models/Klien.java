package models;

public class Klien {
    private int idKlien;
    private String nama;
    private String alamat;
    private String noTelp;
    private String email;

    public Klien() {}

    public Klien(int idKlien, String nama, String alamat, String noTelp, String email) {
        this.idKlien = idKlien;
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.email = email;
    }

    public int getIdKlien() {
        return idKlien;
    }

    public void setIdKlien(int idKlien) {
        this.idKlien = idKlien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

