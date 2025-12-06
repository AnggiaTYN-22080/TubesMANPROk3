package repositories;

import models.User;
import core.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public User findByUsernameAndPassword(String username, String password) {
        String sql = """
            SELECT 
                u.IdUser, u.Nama, u.Alamat, u.NoTelp, u.Username, u.Password,
                CASE 
                    WHEN a.IdAsisten IS NOT NULL THEN 'asisten'
                    WHEN p.IdPemilik IS NOT NULL THEN 'pemilik'
                    ELSE 'unknown'
                END AS Role
            FROM [User] u
            LEFT JOIN Asisten a ON u.IdUser = a.IdAsisten
            LEFT JOIN PemilikUsaha p ON u.IdUser = p.IdPemilik
            WHERE u.Username = ? AND u.Password = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User u = new User(
                        rs.getInt("IdUser"),
                        rs.getString("Nama"),
                        rs.getString("Alamat"),
                        rs.getString("NoTelp"),
                        rs.getString("Username"),
                        rs.getString("Password")
                );
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findUserRole(int idUser) {
        String sql = """
            SELECT 
                CASE 
                    WHEN a.IdAsisten IS NOT NULL THEN 'asisten'
                    WHEN p.IdPemilik IS NOT NULL THEN 'pemilik'
                    ELSE 'unknown'
                END AS Role
            FROM [User] u
            LEFT JOIN Asisten a ON u.IdUser = a.IdAsisten
            LEFT JOIN PemilikUsaha p ON u.IdUser = p.IdPemilik
            WHERE u.IdUser = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getString("Role");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "unknown";
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM [User]";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User u = new User(
                        rs.getInt("IdUser"),
                        rs.getString("Nama"),
                        rs.getString("Alamat"),
                        rs.getString("NoTelp"),
                        rs.getString("Username"),
                        rs.getString("Password")
                );
                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insert(User user) {
        String sql = "INSERT INTO [User](Nama, Alamat, NoTelp, Username, Password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getNama());
            stmt.setString(2, user.getAlamat());
            stmt.setString(3, user.getNoTelp());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getPassword());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public boolean update(User user) {
        String sql = "UPDATE [User] SET Nama=?, Alamat=?, NoTelp=?, Username=?, Password=? WHERE IdUser=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getNama());
            stmt.setString(2, user.getAlamat());
            stmt.setString(3, user.getNoTelp());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.getIdUser());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int idUser) {
        String sql = "DELETE FROM [User] WHERE IdUser = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUser);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
