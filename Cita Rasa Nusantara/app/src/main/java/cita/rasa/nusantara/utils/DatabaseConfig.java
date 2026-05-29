package cita.rasa.nusantara.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    private static final String DB_URL = "jdbc:sqlite:citarasa.db";

    static {
        
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            
            
            stmt.execute("CREATE TABLE IF NOT EXISTS foods (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "description TEXT, " +
                    "price REAL NOT NULL, " +
                    "island TEXT, " +
                    "city TEXT, " +
                    "image_path TEXT)");

            
            stmt.execute("CREATE TABLE IF NOT EXISTS cart (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "food_id INTEGER NOT NULL, " +
                    "quantity INTEGER NOT NULL, " +
                    "FOREIGN KEY (food_id) REFERENCES foods(id))");

            
            stmt.execute("CREATE TABLE IF NOT EXISTS orders (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "recipient_name TEXT NOT NULL, " +
                    "customer_type TEXT NOT NULL, " + // Menyimpan tipe: Regular, Member, VIP
                    "total_amount REAL NOT NULL, " +
                    "order_date TEXT NOT NULL, " +
                    "table_number TEXT NOT NULL)"); // Menyimpan nomor meja terpilih

            
            stmt.execute("CREATE TABLE IF NOT EXISTS order_items (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "order_id INTEGER NOT NULL, " +
                    "food_id INTEGER NOT NULL, " +
                    "quantity INTEGER NOT NULL, " +
                    "price REAL NOT NULL, " +
                    "FOREIGN KEY (order_id) REFERENCES orders(id), " +
                    "FOREIGN KEY (food_id) REFERENCES foods(id))");

            
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "username TEXT PRIMARY KEY, " +
                    "password TEXT NOT NULL, " +
                    "role TEXT NOT NULL)"); // admin / customer

            
            stmt.execute("CREATE TABLE IF NOT EXISTS restaurant_tables (" +
                    "table_number TEXT PRIMARY KEY, " +
                    "status TEXT NOT NULL DEFAULT 'TERSEDIA')"); // TERSEDIA / DIGUNAKAN

            
            stmt.execute("INSERT OR IGNORE INTO restaurant_tables (table_number, status) VALUES " +
                    "('Meja 01', 'TERSEDIA'), ('Meja 02', 'TERSEDIA'), " +
                    "('Meja 03', 'TERSEDIA'), ('Meja 04', 'TERSEDIA'), ('Meja 05', 'TERSEDIA')");

            
            stmt.execute("INSERT OR IGNORE INTO users (username, password, role) VALUES " +
                    "('admin', 'admin123', 'admin'), ('customer', 'user123', 'customer')");

            
            stmt.execute("INSERT OR IGNORE INTO foods (id, name, description, price, island, city, image_path) VALUES " +
                    "(1, 'Coto Makassar', 'Sup daging sapi kaya rempah khas Makassar', 35000, 'Sulawesi', 'Makassar', '/images/coto.jpg'), " +
                    "(2, 'Rendang Padang', 'Daging sapi bumbu karamel rempah otentik', 45000, 'Sumatra', 'Padang', '/images/rendang.jpg'), " +
                    "(3, 'Gudeg Yogyakarta', 'Nangka muda manis gurih dengan krecek', 28000, 'Jawa', 'Yogyakarta', '/images/gudeg.jpg')");

        } catch (SQLException e) {
            System.err.println("Gagal menginisialisasi database: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}