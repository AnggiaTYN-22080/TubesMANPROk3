package views;

import app.App;
import models.JenisVendor;
import models.Vendor;
import services.VendorService;

import java.util.List;

public class VendorView {

    private final VendorService service = new VendorService();

    public void menuJenisVendor() {
        while (true) {
            System.out.println("\n=== JENIS VENDOR ===");
            List<JenisVendor> list = service.getAllJenisVendor();

            System.out.printf("%-5s %-20s\n", "ID", "Nama Jenis Vendor");
            System.out.println("------------------------------");
            for (JenisVendor jv : list) {
                System.out.printf("%-5d %-20s\n", jv.getIdJenisVendor(), jv.getNama());
            }

            System.out.println("\n1. Tambah Jenis Vendor");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            int pil = Integer.parseInt(App.sc.nextLine());

            switch (pil) {
                case 1 -> tambahJenisVendor();
                case 0 -> { return; }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void tambahJenisVendor() {
        System.out.print("Nama jenis vendor baru: ");
        String nama = App.sc.nextLine();

        boolean ok = service.addJenisVendor(nama);
        System.out.println(ok ? "Jenis vendor berhasil ditambahkan." : "Gagal menambah jenis vendor.");
    }
}