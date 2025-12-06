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

    private void showTambahKlien() {
        System.out.println("\n=== TAMBAH KLIEN BARU ===");

        System.out.print("Nama Klien   : ");
        String nama = App.sc.nextLine();

        System.out.print("Alamat       : ");
        String alamat = App.sc.nextLine();

        System.out.print("No. Telepon  : ");
        String telp = App.sc.nextLine();

        System.out.print("Email        : ");
        String email = App.sc.nextLine();

        boolean ok = service.tambahKlien(nama, alamat, telp, email);

        System.out.println(ok ? "Klien berhasil ditambahkan!" : "Gagal menambah klien.");
    }

    private void showKlienSaya() {
        System.out.println("\n=== KLIEN YANG SAYA TANGANI ===");

        List<Klien> list = service.getKlienByAsisten();

        for (Klien k : list) {
            System.out.println(k.getIdKlien() + ". " + k.getNama() +
                            " | " + k.getEmail());
        }
    }
}