package cita.rasa.nusantara.dao;

import cita.rasa.nusantara.utils.DatabaseConfig;
import java.sql.*;

public class PaymentDao {
    public PaymentDao() {
        setupTable();
    }

    public void setupTable() {
        String sql = "CREATE TABLE IF NOT EXISTS payments (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "order_id INTEGER NOT NULL," +
                "amount REAL NOT NULL," +
                "payment_type TEXT NOT NULL," +
                "payment_status TEXT NOT NULL," +
                "FOREIGN KEY (order_id) REFERENCES orders(id)" +
                ");";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
