package repositories;

import core.DBConnection;
import models.JenisVendor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JenisVendorRepository {
    public List<JenisVendor> findAll() {
        List<JenisVendor> list = new ArrayList<>();
        String sql = "SELECT * FROM JenisVendor";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new JenisVendor(
                        rs.getInt("IdJenisVendor"),
                        rs.getString("Nama")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public JenisVendor findById(int id) {
        String sql = "SELECT * FROM JenisVendor WHERE IdJenisVendor = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new JenisVendor(
                        rs.getInt("IdJenisVendor"),
                        rs.getString("Nama")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insert(String nama) {
        String sql = "INSERT INTO JenisVendor (Nama) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nama);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
