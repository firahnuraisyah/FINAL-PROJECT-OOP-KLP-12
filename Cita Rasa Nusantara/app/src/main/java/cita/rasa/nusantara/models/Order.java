package cita.rasa.nusantara.models;

import java.util.Date;

public class Order {
    private int id;
    private String recipientName;
    private double totalAmount;
    private String paymentMethod;
    private Date orderDate;

    public Order(int id, String recipientName, double totalAmount, String paymentMethod, Date orderDate) {
        this.id = id;
        this.recipientName = recipientName;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
