package rentalapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {
    private static Connection connection;
    
    // Sesuaikan nama database, port, user, dan password dengan MySQL Anda
    private static final String URL = "rentalapp"; 
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DbConn() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Registrasi driver (opsional untuk versi JDBC terbaru, tapi aman untuk disertakan)
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Koneksi Database Berhasil!");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Koneksi Database Gagal: " + e.getMessage());
            }
        }
        return connection;
    }
}