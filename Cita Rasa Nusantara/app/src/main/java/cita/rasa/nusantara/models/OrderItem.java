package cita.rasa.nusantara.models;

public class OrderItem {
    private int id;
    private int orderId;
    private Food food;
    private int quantity;
    private double price;

    public OrderItem(int id, int orderId, Food food, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.food = food;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
