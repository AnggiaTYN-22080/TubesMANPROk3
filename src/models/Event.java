package models;

import java.sql.Date;

public class Event {
    private int idEvent;
    private String nama;
    private Date tanggal;
    private int jumlahUndangan;
    private String status;
    private double budget;
    private int idJenisEvent;
    private int idKlien;
    private int idAsisten;

    public Event() {}

    public Event(int idEvent, String nama, Date tanggal, int jumlahUndangan, String status,
                 double budget, int idJenisEvent, int idKlien, int idAsisten) {
        this.idEvent = idEvent;
        this.nama = nama;
        this.tanggal = tanggal;
        this.jumlahUndangan = jumlahUndangan;
        this.status = status;
        this.budget = budget;
        this.idJenisEvent = idJenisEvent;
        this.idKlien = idKlien;
        this.idAsisten = idAsisten;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getJumlahUndangan() {
        return jumlahUndangan;
    }

    public void setJumlahUndangan(int jumlahUndangan) {
        this.jumlahUndangan = jumlahUndangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getIdJenisEvent() {
        return idJenisEvent;
    }

    public void setIdJenisEvent(int idJenisEvent) {
        this.idJenisEvent = idJenisEvent;
    }

    public int getIdKlien() {
        return idKlien;
    }

    public void setIdKlien(int idKlien) {
        this.idKlien = idKlien;
    }

    public int getIdAsisten() {
        return idAsisten;
    }

    public void setIdAsisten(int idAsisten) {
        this.idAsisten = idAsisten;
    }
}
