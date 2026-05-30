package citarasa.services;

import citarasa.utils.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableService {

  
    public List<String> getAvailableTables() {
        List<String> tables = new ArrayList<>();
        String query = "SELECT table_number FROM restaurant_tables WHERE status = 'TERSEDIA'";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                tables.add(rs.getString("table_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    
    public void occupyTable(String tableNumber) {
        String query = "UPDATE restaurant_tables SET status = 'DIGUNAKAN' WHERE table_number = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, tableNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}