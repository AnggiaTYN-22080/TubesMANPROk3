package services;

import core.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventService {

    public List<String[]> getEventByStatus(String status) {
        List<String[]> list = new ArrayList<>();

        String sql = """
            SELECT 
                e.IdEvent,
                e.Nama,
                u_k.Nama AS NamaKlien,
                u_a.Nama AS NamaAsisten,
                e.Tanggal
            FROM Event e
            JOIN Klien k ON e.IdKlien = k.IdKlien
            JOIN [User] u_k ON k.IdKlien = u_k.IdUser
            JOIN Asisten a ON e.IdAsisten = a.IdAsisten
            JOIN [User] u_a ON a.IdAsisten = u_a.IdUser
            WHERE e.Status = ?
            ORDER BY e.Tanggal ASC
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new String[]{
                        String.valueOf(rs.getInt("IdEvent")),
                        rs.getString("Nama"),
                        rs.getString("NamaKlien"),
                        rs.getString("NamaAsisten"),
                        String.valueOf(rs.getDate("Tanggal"))
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String[]> getVendorDetailByEvent(int idEvent) {
        List<String[]> list = new ArrayList<>();

        String sql = """
            SELECT 
                v.Nama,
                jv.Nama AS JenisVendor,
                v.Harga
            FROM EventVendor ev
            JOIN Vendor v ON ev.IdVendor = v.IdVendor
            JOIN JenisVendor jv ON v.IdJenisVendor = jv.IdJenisVendor
            WHERE ev.IdEvent = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEvent);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new String[]{
                        rs.getString("Nama"),
                        rs.getString("JenisVendor"),
                        String.valueOf(rs.getDouble("Harga"))
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Header event (untuk detail)
    public String[] getEventHeader(int idEvent) {
        String sql = """
            SELECT 
                e.Nama,
                u_k.Nama AS NamaKlien,
                u_a.Nama AS NamaAsisten,
                e.Tanggal
            FROM Event e
            JOIN Klien k ON e.IdKlien = k.IdKlien
            JOIN [User] u_k ON k.IdKlien = u_k.IdUser
            JOIN Asisten a ON e.IdAsisten = a.IdAsisten
            JOIN [User] u_a ON a.IdAsisten = u_a.IdUser
            WHERE e.IdEvent = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEvent);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new String[]{
                        rs.getString("Nama"),
                        rs.getString("NamaKlien"),
                        rs.getString("NamaAsisten"),
                        String.valueOf(rs.getDate("Tanggal"))
                };
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
