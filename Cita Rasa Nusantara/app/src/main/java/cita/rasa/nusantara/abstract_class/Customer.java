package cita.rasa.nusantara.abstract_class;

public abstract class Customer {
    protected String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateDiscount(double totalAmount);
}
