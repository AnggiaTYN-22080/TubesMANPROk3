package services;

import core.UserSession;
import java.sql.Date;
import java.util.List;
import models.Event;
import models.Klien;
import repositories.EventRepository;
import repositories.KlienRepository;

public class AsistenService {

    private final KlienRepository klienRepo = new KlienRepository();
    private final EventRepository eventRepo = new EventRepository();

    
    public List<Klien> getDaftarKlien() {
        return klienRepo.findAll();
    }

    public boolean tambahEvent(String namaEvent, String tanggal, int undangan,
                               double budget, int idJenisEvent, int idKlien) {

        int idAsisten = UserSession.getUserId();

        Event event = new Event();
        event.setNama(namaEvent);
        event.setTanggal(Date.valueOf(tanggal));
        event.setJumlahUndangan(undangan);
        event.setStatus("On Progress");
        event.setBudget(budget);
        event.setIdJenisEvent(idJenisEvent);
        event.setIdKlien(idKlien);
        event.setIdAsisten(idAsisten);

        return eventRepo.insert(event);
    }

    public boolean alokasikanVendor(int idEvent, int idVendor, double hargaDealing) {
        return eventRepo.alokasikanVendor(idEvent, idVendor, hargaDealing);
    }

    public boolean updateStatus(int idEvent, String statusBaru) {
        return eventRepo.updateStatus(idEvent, statusBaru);
    }

    public boolean tambahKlien(String nama, String alamat, String telp, String email) {
        Klien k = new Klien(0, nama, alamat, telp, email);
        return klienRepo.insert(k);
    }

    public List<Event> getEventByAsisten() {
    int id = UserSession.getUserId();
    return eventRepo.getEventsByAsisten(id);
    }

    public List<Klien> getKlienByAsisten() {
        int id = UserSession.getUserId();
        return eventRepo.getKlienByAsisten(id);
    }

    public List<String[]> getDetailKlienEvent() {
        int id = UserSession.getUserId();
        return eventRepo.getDetailKlienEventByAsisten(id);
    }
}
