package repositories;

import core.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Event;
import models.Klien;

public class EventRepository {

    public boolean insert(Event event) {
        String sql = """
            INSERT INTO [Event] 
            (Nama, Tanggal, JumlahUndangan, Status, Budget, IdJenisEvent, IdKlien, IdAsisten)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getNama());
            stmt.setDate(2, event.getTanggal());
            stmt.setInt(3, event.getJumlahUndangan());
            stmt.setString(4, event.getStatus());
            stmt.setDouble(5, event.getBudget());
            stmt.setInt(6, event.getIdJenisEvent());
            stmt.setInt(7, event.getIdKlien());
            stmt.setInt(8, event.getIdAsisten());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateStatus(int idEvent, String status) {
        String sql = "UPDATE [Event] SET Status = ? WHERE IdEvent = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, idEvent);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean alokasikanVendor(int idEvent, int idVendor, double hargaDealing) {
        String sql = "INSERT INTO EventVendor (IdEvent, IdVendor, HargaDealing) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEvent);
            stmt.setInt(2, idVendor);
            stmt.setDouble(3, hargaDealing);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<Event> getEventsByAsisten(int idAsisten) {
        List<Event> list = new ArrayList<>();

        String sql = "SELECT * FROM [Event] WHERE IdAsisten = ? ORDER BY Tanggal ASC";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAsisten);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Event e = new Event(
                        rs.getInt("IdEvent"),
                        rs.getString("Nama"),
                        rs.getDate("Tanggal"),
                        rs.getInt("JumlahUndangan"),
                        rs.getString("Status"),
                        rs.getDouble("Budget"),
                        rs.getInt("IdJenisEvent"),
                        rs.getInt("IdKlien"),
                        rs.getInt("IdAsisten")
                );
                list.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Klien> getKlienByAsisten(int idAsisten) {
        List<Klien> list = new ArrayList<>();

        String sql = """
            SELECT DISTINCT k.*
            FROM Klien k
            JOIN [Event] e ON k.IdKlien = e.IdKlien
            WHERE e.IdAsisten = ?
        """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAsisten);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Klien k = new Klien(
                        rs.getInt("IdKlien"),
                        rs.getString("Nama"),
                        rs.getString("Alamat"),
                        rs.getString("NoTelp"),
                        rs.getString("Email")
                );
                list.add(k);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String[]> getDetailKlienEventByAsisten(int idAsisten) {
        List<String[]> list = new ArrayList<>();

        String sql = """
            SELECT u.Nama AS NamaAsisten, k.Nama AS NamaKlien, 
                e.Nama AS NamaEvent, e.Tanggal, e.JumlahUndangan,
                e.Budget, je.Nama AS JenisEvent
            FROM Asisten a
            JOIN [User] u ON a.IdAsisten = u.IdUser
            JOIN [Event] e ON e.IdAsisten = a.IdAsisten
            JOIN Klien k ON k.IdKlien = e.IdKlien
            JOIN JenisEvent je ON je.IdJenisEvent = e.IdJenisEvent
            WHERE a.IdAsisten = ?
            ORDER BY k.IdKlien, e.IdEvent
        """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAsisten);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new String[]{
                        rs.getString("NamaAsisten"),
                        rs.getString("NamaKlien"),
                        rs.getString("NamaEvent"),
                        String.valueOf(rs.getDate("Tanggal")),
                        String.valueOf(rs.getInt("JumlahUndangan")),
                        String.valueOf(rs.getDouble("Budget")),
                        rs.getString("JenisEvent")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
