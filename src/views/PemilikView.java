import app.App;
import core.UserSession;
import models.User;
import services.PemilikService;

import java.util.List;

public class PemilikView {

    private final PemilikService service = new PemilikService();

    public void menuPemilik() {

        while (true) {
            System.out.println("\n=== MENU PEMILIK USAHA ===");
            System.out.println("Selamat datang, " + UserSession.getNama());
            System.out.println("1. Kelola Asisten");
            System.out.println("2. Kelola Vendor");
            System.out.println("3. Kelola Jenis Vendor");
            System.out.println("4. Laporan Event");
            System.out.println("5. Kinerja Asisten");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");

            int pil = Integer.parseInt(App.sc.nextLine());

            switch (pil) {
                case 1 -> kelolaAsisten();
                case 2 -> new VendorView().menuKelolaVendor();
                case 3 -> new VendorView().menuJenisVendor();
                case 4 -> new EventView().menuLaporan();
                case 5 -> laporanKinerjaAsisten();
                case 0 -> {
                    System.out.println("Logout berhasil.");
                    UserSession.clear();
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}