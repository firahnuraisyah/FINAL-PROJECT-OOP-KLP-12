package cita.rasa.nusantara.dao;

import cita.rasa.nusantara.abstract_class.DaoForCart;
import cita.rasa.nusantara.models.CartItem;
import cita.rasa.nusantara.models.Food;
import cita.rasa.nusantara.utils.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao extends DaoForCart {

    public CartDao() {
        setupTable();
    }

    @Override
    public void setupTable() {
        String sql = "CREATE TABLE IF NOT EXISTS cart_items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "food_id INTEGER NOT NULL," +
                "quantity INTEGER NOT NULL," +
                "FOREIGN KEY (food_id) REFERENCES foods(id)" +
                ");";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CartItem> getCartItems() {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT c.id, c.food_id, c.quantity, f.name, f.description, f.price, f.island, f.city, f.image_path " +
                "FROM cart_items c JOIN foods f ON c.food_id = f.id";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Food food = new Food(
                        rs.getInt("food_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("island"),
                        rs.getString("city"),
                        rs.getString("image_path")
                );
                items.add(new CartItem(rs.getInt("id"), food, rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void addToCart(int foodId, int quantity) {
        String checkSql = "SELECT id, quantity FROM cart_items WHERE food_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(checkSql)) {
            pstmt.setInt(1, foodId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    updateCart(rs.getInt("id"), rs.getInt("quantity") + quantity);
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO cart_items(food_id, quantity) VALUES(?,?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, foodId);
            pstmt.setInt(2, quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCart(int cartId, int quantity) {
        if (quantity <= 0) {
            deleteCart(cartId);
            return;
        }
        String sql = "UPDATE cart_items SET quantity = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, cartId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCart(int cartId) {
        String sql = "DELETE FROM cart_items WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cartId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearCart() {
        String sql = "DELETE FROM cart_items";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}