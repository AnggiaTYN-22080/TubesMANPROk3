package services;

import core.DBConnection;
import models.User;
import repositories.AsistenRepository;
import repositories.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PemilikService {

    private final UserRepository userRepo = new UserRepository();
    private final AsistenRepository asistenRepo = new AsistenRepository();

    public List<User> getAllAsisten() {
        List<User> list = new ArrayList<>();

        String sql = """
            SELECT 
                u.IdUser, u.Nama, u.Username, u.Password, u.Alamat, u.NoTelp
            FROM Asisten a 
            JOIN [User] u ON a.IdAsisten = u.IdUser
        """;

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addAsisten(User u) {
        int id = userRepo.insert(u);
        if (id == -1) return false;
        return asistenRepo.insert(id);
    }

    public boolean editAsisten(User u) {
        return userRepo.update(u);
    }

    public boolean deleteAsisten(int id) {
        return asistenRepo.delete(id) && userRepo.delete(id);
    }


    public List<String[]> getLaporanEventAsisten(int idAsisten) {
        List<String[]> list = new ArrayList<>();

        String sql = """
            SELECT 
                e.IdEvent,
                e.Nama AS NamaEvent,
                e.Tanggal,
                e.Status,
                u_k.Nama AS NamaKlien
            FROM Event e
            JOIN Klien k ON e.IdKlien = k.IdKlien
            JOIN [User] u_k ON k.IdKlien = u_k.IdUser
            WHERE e.IdAsisten = ?
            ORDER BY e.Tanggal ASC
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAsisten);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new String[]{
                        String.valueOf(rs.getInt("IdEvent")),
                        rs.getString("NamaEvent"),
                        String.valueOf(rs.getDate("Tanggal")),
                        rs.getString("Status"),
                        rs.getString("NamaKlien")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
