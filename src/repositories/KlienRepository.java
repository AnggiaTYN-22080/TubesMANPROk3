package repositories;

import core.DBConnection;
import models.Klien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KlienRepository {

    public List<Klien> findAll() {
        List<Klien> list = new ArrayList<>();
        String sql = "SELECT * FROM Klien";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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

    public boolean insert(Klien k) {
        String sql = """
            INSERT INTO Klien (Nama, Alamat, NoTelp, Email)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, k.getNama());
            stmt.setString(2, k.getAlamat());
            stmt.setString(3, k.getNoTelp());
            stmt.setString(4, k.getEmail());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}