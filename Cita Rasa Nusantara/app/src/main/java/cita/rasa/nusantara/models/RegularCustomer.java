package cita.rasa.nusantara.models;

import cita.rasa.nusantara.abstract_class.Customer;

public class RegularCustomer extends Customer {
    public RegularCustomer(String name) {
        super(name);
    }

    @Override
    public double calculateDiscount(double totalAmount) {
        return 0;
    }
}
