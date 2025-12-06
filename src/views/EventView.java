package views;

import app.App;
import services.EventService;

import java.util.List;

public class EventView {

    private final EventService service = new EventService();

    public void menuLaporan() {
        while (true) {
            System.out.println("\n=== LAPORAN EVENT ===");
            System.out.println("1. Event On Progress");
            System.out.println("2. Event Akan Datang");
            System.out.println("3. Event Selesai");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");

            int pil = Integer.parseInt(App.sc.nextLine());

            if (pil == 0) return;

            String status = switch (pil) {
                case 1 -> "On Progress";
                case 2 -> "Akan Datang";
                case 3 -> "Selesai";
                default -> null;
            };

            if (status == null) {
                System.out.println("Pilihan tidak valid.");
                continue;
            }

            showEventList(status);
        }
    }

    private void showEventList(String status) {
        List<String[]> list = service.getEventByStatus(status);

        System.out.println("\n=== DAFTAR EVENT (" + status + ") ===");
        for (String[] e : list) {
            System.out.println(e[0] + ". " + e[1] + " | Klien: " + e[2] +
                    " | Asisten: " + e[3] + " | Tgl: " + e[4]);
        }

        System.out.print("Lihat detail ID Event (0=back): ");
        int id = Integer.parseInt(App.sc.nextLine());
        if (id == 0) return;

        showEventDetail(id);
    }

    private void showEventDetail(int idEvent) {
        System.out.println("\n=== DETAIL EVENT ===");

        String[] header = service.getEventHeader(idEvent);
        if (header != null) {
            System.out.println("Nama Event : " + header[0]);
            System.out.println("Klien      : " + header[1]);
            System.out.println("Asisten    : " + header[2]);
            System.out.println("Tanggal    : " + header[3]);
        }

        List<String[]> vendors = service.getVendorDetailByEvent(idEvent);

        System.out.printf("\n%-20s | %-15s | %s\n", "Vendor", "Jenis", "Harga");
        System.out.println("-----------------------------------------------");

        double total = 0;

        for (String[] v : vendors) {
            double harga = Double.parseDouble(v[2]);
            System.out.printf("%-20s | %-15s | %.2f\n", v[0], v[1], harga);
            total += harga;
        }

        System.out.println("\nTOTAL BIAYA VENDOR: Rp " + total);
    }
}