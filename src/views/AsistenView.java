package views;

import app.App;

import java.text.DecimalFormat;
import java.util.List;
import models.Event;
import models.Klien;
import services.AsistenService;

public class AsistenView {

    private final AsistenService service = new AsistenService();

    public void menuAsisten() {

        while (true) {
            System.out.println("\n=== MENU ASISTEN ===");
            System.out.println("1. Lihat Daftar Klien");
            System.out.println("2. Tambah Event");
            System.out.println("3. Alokasikan Vendor ke Event");
            System.out.println("4. Update Status Event");
            System.out.println("5. Tambah Klien Baru");
            System.out.println("6. Lihat Event Saya");
            System.out.println("7. Lihat Klien Saya");
            System.out.println("8. Lihat Detail Klien & Event Saya");
            System.out.println("0. Logout");

            System.out.print("Pilih: ");

            int pil = Integer.parseInt(App.sc.nextLine());

            switch (pil) {
                case 1 -> showKlien();
                case 2 -> showTambahEvent();
                case 3 -> showAlokasiVendor();
                case 4 -> showUpdateStatus();
                case 5 -> showTambahKlien();
                case 6 -> showEventSaya();
                case 7 -> showKlienSaya();
                case 8 -> showDetailKlienEvent();
                case 0 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}