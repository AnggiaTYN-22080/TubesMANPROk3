package models;

public class User {
    private int idUser;
    private String nama;
    private String alamat;
    private String noTelp;
    private String username;
    private String password;

    public User() {}

    public User(int idUser, String nama) {
        this.idUser = idUser;
        this.nama = nama;
    }

    public User(int idUser, String nama, String alamat, String noTelp, String username, String password) {
        this.idUser = idUser;
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.username = username;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
