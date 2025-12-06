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

    private void kelolaAsisten() {
        System.out.println("\n=== DAFTAR ASISTEN ===");
        List<User> list = service.getAllAsisten();

        for (User u : list) {
            System.out.println(u.getIdUser() + ". " + u.getNama() + " (" + u.getUsername() + ")");
        }

        System.out.println("\n1. Tambah Asisten");
        System.out.println("2. Edit Asisten");
        System.out.println("3. Hapus Asisten");
        System.out.println("0. Kembali");
        System.out.print("Pilih: ");

        int pil = Integer.parseInt(App.sc.nextLine());

        switch (pil) {
            case 1 -> tambahAsisten();
            case 2 -> editAsisten();
            case 3 -> hapusAsisten();
            case 0 -> {
            }
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    private void tambahAsisten() {
        System.out.println("\n=== TAMBAH ASISTEN ===");

        System.out.print("Nama: ");
        String nama = App.sc.nextLine();

        System.out.print("Alamat: ");
        String alamat = App.sc.nextLine();

        System.out.print("Telp: ");
        String telp = App.sc.nextLine();

        System.out.print("Username: ");
        String user = App.sc.nextLine();

        System.out.print("Password: ");
        String pass = App.sc.nextLine();

        User u = new User(0, nama, alamat, telp, user, pass);

        boolean ok = service.addAsisten(u);
        System.out.println(ok ? "Asisten ditambahkan." : "Gagal menambah.");
    }

    private void editAsisten() {
        System.out.print("ID Asisten: ");
        int id = Integer.parseInt(App.sc.nextLine());

        System.out.print("Nama baru: ");
        String nama = App.sc.nextLine();

        System.out.print("Alamat: ");
        String alamat = App.sc.nextLine();

        System.out.print("NoTelp: ");
        String telp = App.sc.nextLine();

        System.out.print("Username: ");
        String user = App.sc.nextLine();

        System.out.print("Password: ");
        String pass = App.sc.nextLine();

        User u = new User(id, nama, alamat, telp, user, pass);

        boolean ok = service.editAsisten(u);
        System.out.println(ok ? "Berhasil update." : "Gagal update.");
    }

    private void hapusAsisten() {
        System.out.print("ID Asisten: ");
        int id = Integer.parseInt(App.sc.nextLine());

        boolean ok = service.deleteAsisten(id);
        System.out.println(ok ? "Berhasil hapus." : "Gagal hapus.");
    }
    
}