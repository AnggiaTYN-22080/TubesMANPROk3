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

    private void showTambahEvent() {
        System.out.println("\n=== TAMBAH EVENT ===");

        System.out.print("Nama Event: ");
        String nama = App.sc.nextLine();

        System.out.print("Tanggal (YYYY-MM-DD): ");
        String tanggal = App.sc.nextLine();

        System.out.print("Jumlah Undangan: ");
        int undangan = Integer.parseInt(App.sc.nextLine());

        System.out.print("Budget: ");
        double budget = Double.parseDouble(App.sc.nextLine());

        System.out.print("ID Jenis Event: ");
        int idJenis = Integer.parseInt(App.sc.nextLine());

        System.out.print("ID Klien: ");
        int idKlien = Integer.parseInt(App.sc.nextLine());

        boolean ok = service.tambahEvent(nama, tanggal, undangan, budget, idJenis, idKlien);

        System.out.println(ok ? "Event berhasil ditambah!" : "Gagal menambah event.");
    }

    private void showAlokasiVendor() {
        System.out.println("\n=== ALOKASIKAN VENDOR ===");

        System.out.print("ID Event: ");
        int idEvent = Integer.parseInt(App.sc.nextLine());

        System.out.print("ID Vendor: ");
        int idVendor = Integer.parseInt(App.sc.nextLine());

        System.out.print("Harga Dealing: ");
        double harga = Double.parseDouble(App.sc.nextLine());

        boolean ok = service.alokasikanVendor(idEvent, idVendor, harga);

        System.out.println(ok ? "Vendor berhasil dialokasikan." : "Gagal mengalokasikan vendor.");
    }

    private void showUpdateStatus() {
        System.out.println("\n=== UPDATE STATUS EVENT ===");

        System.out.print("ID Event: ");
        int idEvent = Integer.parseInt(App.sc.nextLine());

        System.out.print("Status baru: ");
        String status = App.sc.nextLine();

        boolean ok = service.updateStatus(idEvent, status);

        System.out.println(ok ? "Status berhasil diupdate." : "Gagal update status.");
    }
}