package cita.rasa.nusantara.models;

public class Food {
    private int id;
    private String name;
    private String description;
    private double price;
    private String island;
    private String city;
    private String imagePath;

    public Food(int id, String name, String description, double price, String island, String city, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.island = island;
        this.city = city;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsland() {
        return island;
    }

    public void setIsland(String island) {
        this.island = island;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
