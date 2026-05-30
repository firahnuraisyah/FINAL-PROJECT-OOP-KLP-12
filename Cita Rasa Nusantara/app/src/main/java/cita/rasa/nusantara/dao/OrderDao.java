package cita.rasa.nusantara.dao;

import cita.rasa.nusantara.abstract_class.DaoForOrders;
import cita.rasa.nusantara.models.Order;
import cita.rasa.nusantara.models.OrderItem;
import cita.rasa.nusantara.utils.DatabaseConfig;

import java.sql.*;

public class OrderDao extends DaoForOrders {

    public OrderDao() {
        setupTable();
    }

    @Override
    public void setupTable() {
        String orderSql = "CREATE TABLE IF NOT EXISTS orders (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "recipient_name TEXT NOT NULL," +
                "total_amount REAL NOT NULL," +
                "payment_method TEXT NOT NULL," +
                "order_date DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ");";
        String itemSql = "CREATE TABLE IF NOT EXISTS order_items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "order_id INTEGER NOT NULL," +
                "food_id INTEGER NOT NULL," +
                "quantity INTEGER NOT NULL," +
                "price REAL NOT NULL," +
                "FOREIGN KEY (order_id) REFERENCES orders(id)," +
                "FOREIGN KEY (food_id) REFERENCES foods(id)" +
                ");";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(orderSql);
            stmt.execute(itemSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO orders(recipient_name, total_amount, payment_method) VALUES(?,?,?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, order.getRecipientName());
            pstmt.setDouble(2, order.getTotalAmount());
            pstmt.setString(3, order.getPaymentMethod());
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void saveOrderItem(OrderItem item) {
        String sql = "INSERT INTO order_items(order_id, food_id, quantity, price) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, item.getOrderId());
            pstmt.setInt(2, item.getFood().getId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.setDouble(4, item.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
