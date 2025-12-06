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

    public boolean tambahKlien(String nama, String alamat, String telp, String email) {
        Klien k = new Klien(0, nama, alamat, telp, email);
        return klienRepo.insert(k);
    }

    public List<Klien> getKlienByAsisten() {
        int id = UserSession.getUserId();
        return eventRepo.getKlienByAsisten(id);
    }

}