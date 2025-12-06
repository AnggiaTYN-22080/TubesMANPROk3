package models;

public class JenisEvent {
    private int idJenisEvent;
    private String nama;

    public JenisEvent() {}

    public JenisEvent(int idJenisEvent, String nama) {
        this.idJenisEvent = idJenisEvent;
        this.nama = nama;
    }

    public int getIdJenisEvent() {
        return idJenisEvent;
    }

    public void setIdJenisEvent(int idJenisEvent) {
        this.idJenisEvent = idJenisEvent;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
