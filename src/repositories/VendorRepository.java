package repositories;

import core.DBConnection;
import models.Vendor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendorRepository {

    public List<Vendor> findAll() {
        List<Vendor> list = new ArrayList<>();
        String sql = "SELECT * FROM Vendor";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Vendor findById(int idVendor) {
        String sql = "SELECT * FROM Vendor WHERE IdVendor = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVendor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insert(Vendor v) {
        String sql = """
            INSERT INTO Vendor (Nama, NamaPemilik, Alamat, NoTelp, Harga, IdJenisVendor)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, v.getNama());
            stmt.setString(2, v.getNamaPemilik());
            stmt.setString(3, v.getAlamat());
            stmt.setString(4, v.getNoTelp());
            stmt.setDouble(5, v.getHarga());
            stmt.setInt(6, v.getIdJenisVendor());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Vendor v) {
        String sql = """
            UPDATE Vendor 
            SET Nama=?, NamaPemilik=?, Alamat=?, NoTelp=?, Harga=?, IdJenisVendor=?
            WHERE IdVendor=?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, v.getNama());
            stmt.setString(2, v.getNamaPemilik());
            stmt.setString(3, v.getAlamat());
            stmt.setString(4, v.getNoTelp());
            stmt.setDouble(5, v.getHarga());
            stmt.setInt(6, v.getIdJenisVendor());
            stmt.setInt(7, v.getIdVendor());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int idVendor) {
        String sql = "DELETE FROM Vendor WHERE IdVendor = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVendor);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Vendor mapRow(ResultSet rs) throws SQLException {
        return new Vendor(
                rs.getInt("IdVendor"),
                rs.getString("Nama"),
                rs.getString("NamaPemilik"),
                rs.getString("Alamat"),
                rs.getString("NoTelp"),
                rs.getDouble("Harga"),
                rs.getInt("IdJenisVendor")
        );
    }
}
