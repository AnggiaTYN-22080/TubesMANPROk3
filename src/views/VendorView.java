package views;

import app.App;
import models.JenisVendor;
import models.Vendor;
import services.VendorService;

import java.util.List;

public class VendorView {

    private final VendorService service = new VendorService();

    public void menuKelolaVendor() {
        while (true) {
            System.out.println("\n=== KELOLA VENDOR ===");
            showDaftarVendor();

            System.out.println("\n1. Tambah Vendor");
            System.out.println("2. Pilih Vendor");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");

            String inp = App.sc.nextLine();
            int pil = inp.isEmpty() ? -1 : Integer.parseInt(inp);

            switch (pil) {
                case 1 -> tambahVendor();
                case 2 -> pilihVendor();
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

    private void showDaftarVendor() {
        List<Vendor> list = service.getAllVendor();
        List<JenisVendor> jenisList = service.getAllJenisVendor();

        System.out.printf("%-5s %-30s %-20s\n", "ID", "Nama Vendor", "Jenis Vendor");
        System.out.println("--------------------------------------------------------");

        for (Vendor v : list) {
            String jenisNama = "-";
            for (JenisVendor jv : jenisList) {
                if (jv.getIdJenisVendor() == v.getIdJenisVendor()) {
                    jenisNama = jv.getNama();
                    break;
                }
            }
            System.out.printf("%-5d %-30s %-20s\n",
                    v.getIdVendor(), v.getNama(), jenisNama);
        }
    }

    private void pilihVendor() {
        System.out.print("Masukkan ID Vendor: ");
        int id = Integer.parseInt(App.sc.nextLine());

        Vendor v = service.getVendorById(id);
        if (v == null) {
            System.out.println("Vendor tidak ditemukan.");
            return;
        }

        JenisVendor jv = service.getJenisVendorById(v.getIdJenisVendor());

        System.out.println("\n=== DETAIL VENDOR ===");
        System.out.println("Nama Vendor   : " + v.getNama());
        System.out.println("Nama Pemilik  : " + v.getNamaPemilik());
        System.out.println("Alamat        : " + v.getAlamat());
        System.out.println("No. Telp      : " + v.getNoTelp());
        System.out.println("Harga         : " + v.getHarga());
        System.out.println("Jenis Vendor  : " + (jv != null ? jv.getNama() : "-"));

        System.out.println("\n1. Edit Vendor");
        System.out.println("2. Hapus Vendor");
        System.out.println("0. Kembali");
        System.out.print("Pilih: ");
        int pil = Integer.parseInt(App.sc.nextLine());

        switch (pil) {
            case 1 -> editVendor(v);
            case 2 -> hapusVendor(v.getIdVendor(), v.getNama());
            case 0 -> {
            }
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    private void tambahVendor() {
        System.out.println("\n=== TAMBAH VENDOR ===");

        System.out.print("Nama Vendor   : ");
        String nama = App.sc.nextLine();

        System.out.print("Nama Pemilik  : ");
        String pemilik = App.sc.nextLine();

        System.out.print("Alamat        : ");
        String alamat = App.sc.nextLine();

        System.out.print("No. Telp      : ");
        String noTelp = App.sc.nextLine();

        System.out.print("Harga         : ");
        double harga = Double.parseDouble(App.sc.nextLine());

        System.out.println("\n=== DAFTAR JENIS VENDOR ===");
        List<JenisVendor> jenisList = service.getAllJenisVendor();
        for (JenisVendor jv : jenisList) {
            System.out.println(jv.getIdJenisVendor() + ". " + jv.getNama());
        }
        System.out.print("Pilih ID Jenis Vendor: ");
        int idJenis = Integer.parseInt(App.sc.nextLine());

        boolean ok = service.addVendor(nama, pemilik, alamat, noTelp, harga, idJenis);
        System.out.println(ok ? "Vendor berhasil ditambahkan." : "Gagal menambah vendor.");
    }

    private void editVendor(Vendor v) {
        while (true) {
            System.out.println("\n=== EDIT VENDOR ===");
            System.out.println("1. Nama          : " + v.getNama());
            System.out.println("2. Nama Pemilik  : " + v.getNamaPemilik());
            System.out.println("3. Alamat        : " + v.getAlamat());
            System.out.println("4. NoTelp        : " + v.getNoTelp());
            System.out.println("5. Harga         : " + v.getHarga());
            System.out.println("6. Jenis Vendor  : " + v.getIdJenisVendor());
            System.out.println("0. Selesai");
            System.out.print("Pilih bagian yang ingin diubah: ");

            int pil = Integer.parseInt(App.sc.nextLine());

            if (pil == 0) break;

            switch (pil) {
                case 1 -> {
                    System.out.print("Nama baru: ");
                    v.setNama(App.sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Nama pemilik baru: ");
                    v.setNamaPemilik(App.sc.nextLine());
                }
                case 3 -> {
                    System.out.print("Alamat baru: ");
                    v.setAlamat(App.sc.nextLine());
                }
                case 4 -> {
                    System.out.print("NoTelp baru: ");
                    v.setNoTelp(App.sc.nextLine());
                }
                case 5 -> {
                    System.out.print("Harga baru: ");
                    v.setHarga(Double.parseDouble(App.sc.nextLine()));
                }
                case 6 -> {
                    System.out.println("=== DAFTAR JENIS VENDOR ===");
                    List<JenisVendor> jenisList = service.getAllJenisVendor();
                    for (JenisVendor jv : jenisList) {
                        System.out.println(jv.getIdJenisVendor() + ". " + jv.getNama());
                    }
                    System.out.print("Pilih ID Jenis Vendor baru: ");
                    int idJenis = Integer.parseInt(App.sc.nextLine());
                    v.setIdJenisVendor(idJenis);
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }

        boolean ok = service.updateVendor(v);
        System.out.println(ok ? "Data vendor berhasil diupdate." : "Gagal update vendor.");
    }

    private void hapusVendor(int idVendor, String namaVendor) {
        System.out.print("Yakin hapus vendor " + namaVendor + "? (y/n): ");
        String ans = App.sc.nextLine();

        if (!ans.equalsIgnoreCase("y")) {
            System.out.println("Penghapusan dibatalkan.");
            return;
        }

        boolean ok = service.deleteVendor(idVendor);
        System.out.println(ok ? "Vendor berhasil dihapus." : "Gagal menghapus vendor.");
    private void tambahJenisVendor() {
        System.out.print("Nama jenis vendor baru: ");
        String nama = App.sc.nextLine();

        boolean ok = service.addJenisVendor(nama);
        System.out.println(ok ? "Jenis vendor berhasil ditambahkan." : "Gagal menambah jenis vendor.");
    }
}