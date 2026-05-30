package cita.rasa.nusantara.dao;

import cita.rasa.nusantara.abstract_class.DaoForFoods;
import cita.rasa.nusantara.models.Food;
import cita.rasa.nusantara.utils.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDao extends DaoForFoods {

    public FoodDao() {
        setupTable();
        seedData();
    }

    @Override
    public void setupTable() {
        String sql = "CREATE TABLE IF NOT EXISTS foods (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL UNIQUE," + 
                "description TEXT," +
                "price REAL," +
                "island TEXT," +
                "city TEXT," +
                "image_path TEXT" +
                ");";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void seedData() {
        String clearSql = "DELETE FROM foods";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(clearSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        insertFood(new Food(0, "Pempek", "Olahan ikan khas Palembang dengan kuah cuko", 35000, "Sumatra", "Palembang", "images/pempek.jpg"));
        insertFood(new Food(0, "Rendang", "Daging sapi bumbu rempah khas Padang", 50000, "Sumatra", "Padang", "images/rendang.jpg"));
        insertFood(new Food(0, "Bika Ambon", "Kue tradisional bertekstur rongga khas Medan", 45000, "Sumatra", "Medan", "images/bika_ambon.jpg"));
        insertFood(new Food(0, "Mie Aceh", "Mie kuning tebal dengan kuah kari pedas khas Aceh", 30000, "Sumatra", "Banda Aceh", "images/mie_aceh.jpg"));

        insertFood(new Food(0, "Gudeg", "Nangka muda dimasak santan manis khas Yogyakarta", 35000, "Jawa", "Yogyakarta", "images/gudeg.jpg"));
        insertFood(new Food(0, "Rawon", "Sup daging kuah hitam pekat buah kluwek khas Surabaya", 40000, "Jawa", "Surabaya", "images/rawon.jpg"));
        insertFood(new Food(0, "Lumpia Semarang", "Lumpia isi rebung dan ayam khas Semarang", 25000, "Jawa", "Semarang", "images/lumpia_semarang.jpg"));
        insertFood(new Food(0, "Batagor", "Bakso tahu goreng disiram saus kacang khas Bandung", 20000, "Jawa", "Bandung", "images/batagor.jpg"));
        insertFood(new Food(0, "Empal Gentong", "Sup daging dan jeroan sapi mirip gulai khas Cirebon", 38000, "Jawa", "Cirebon", "images/empal_gentong.jpg"));

        insertFood(new Food(0, "Soto Banjar", "Soto ayam berkuah bening dengan rempah khas Banjarmasin", 35000, "Kalimantan", "Banjarmasin", "images/soto_banjar.jpg"));
        insertFood(new Food(0, "Chai Kue", "Kue kukus isi bengkoang atau kucai khas Pontianak", 20000, "Kalimantan", "Pontianak", "images/chai_kue.jpg"));
        insertFood(new Food(0, "Nasi Bekepor", "Nasi liwet khas kerajaan Kutai Kartanegara Samarinda", 30000, "Kalimantan", "Samarinda", "images/nasi_bekepor.jpg"));

        insertFood(new Food(0, "Coto Makassar", "Sup daging dan jeroan sapi kaya rempah khas Makassar", 40000, "Sulawesi", "Makassar", "images/coto_makassar.jpg"));
        insertFood(new Food(0, "Tinutuan", "Bubur manado campuran sayuran hijau dan jagung", 25000, "Sulawesi", "Manado", "images/tinutuan.jpg"));
        insertFood(new Food(0, "Kaledo", "Sup kaki sapi khas Palu dengan kuah asam pedas", 45000, "Sulawesi", "Palu", "images/kaledo.jpg"));

        insertFood(new Food(0, "Ayam Betutu", "Ayam panggang bumbu pedas kaya rempah khas Denpasar", 45000, "Bali & Nusa Tenggara", "Denpasar", "images/ayam_betutu.jpg"));
        insertFood(new Food(0, "Ayam Taliwang", "Ayam bakar bumbu pedas khas Lombok", 45000, "Bali & Nusa Tenggara", "Lombok", "images/ayam_taliwang.jpg"));
        insertFood(new Food(0, "Se'i Sapi", "Daging sapi asap khas Kupang Nusa Tenggara Timur", 55000, "Bali & Nusa Tenggara", "Kupang", "images/sei_sapi.jpg"));

        insertFood(new Food(0, "Papeda", "Bubur sagu kenyal khas Ambon Maluku", 20000, "Maluku & Papua", "Ambon", "images/papeda.jpg"));
        insertFood(new Food(0, "Ikan Kuah Kuning", "Sup ikan bumbu kunyit pendamping papeda khas Jayapura", 50000, "Maluku & Papua", "Jayapura", "images/ikan_kuah_kuning.jpg"));
        insertFood(new Food(0, "Udang Selingkuh", "Sajian udang air tawar unik khas Papua Barat", 75000, "Maluku & Papua", "Papua Barat", "images/udang_selingkuh.jpg"));
    }

    @Override
    public List<Food> getFoods() {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM foods";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                foods.add(new Food(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("island"),
                        rs.getString("city"),
                        rs.getString("image_path")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    @Override
    public List<Food> getFoodsByIsland(String island) {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM foods WHERE island = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, island);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    foods.add(new Food(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getString("island"),
                            rs.getString("city"),
                            rs.getString("image_path")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    @Override
    public List<Food> getFoodsByCity(String city) {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM foods WHERE city = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, city);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    foods.add(new Food(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getString("island"),
                            rs.getString("city"),
                            rs.getString("image_path")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    @Override
    public void insertFood(Food food) {
        String sql = "INSERT OR IGNORE INTO foods(name, description, price, island, city, image_path) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, food.getName());
            pstmt.setString(2, food.getDescription());
            pstmt.setDouble(3, food.getPrice());
            pstmt.setString(4, food.getIsland());
            pstmt.setString(5, food.getCity());
            pstmt.setString(6, food.getImagePath());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}