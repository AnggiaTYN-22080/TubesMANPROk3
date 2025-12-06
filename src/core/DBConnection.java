package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String CONNECTION_URL =
            "jdbc:sqlserver://localhost:1433;" +
            "databaseName=Manpro;" +
            "integratedSecurity=true;" +
            "encrypt=false;" +
            "trustServerCertificate=true";

    private DBConnection() {
    }

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("SQL Server JDBC Driver tidak ditemukan.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }

    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Berhasil terhubung ke database!");
            }
        } catch (SQLException e) {
            System.err.println("Gagal koneksi database:");
            e.printStackTrace();
        }
    }
}

    // private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=manpro;encrypt=true;trustServerCertificate=true";
    // private static final String USER = "sa";
    // private static final String PASSWORD = "dockerStrongPwd123";

    // public static Connection getConnection() {
    //     try {
    //         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    //         return DriverManager.getConnection(URL, USER, PASSWORD);
    //     } catch (Exception e) {
    //         throw new RuntimeException("Gagal koneksi DB: " + e.getMessage(), e);
    //     }
    // }

    // public static void main(String[] args) {
    //     try (Connection c = getConnection()) {
    //         System.out.println("Connected to DB ok: " + c.getMetaData().getURL());
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
//}
