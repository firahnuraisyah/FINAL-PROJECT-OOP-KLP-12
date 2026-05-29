package cita.rasa.nusantara.models;

import citarasa.abstract_class.Customer;

public class MemberCustomer extends Customer {
    public MemberCustomer(String name) {
        super(name);
    }

    @Override
    public double calculateDiscount(double totalAmount) {
        return totalAmount * 0.10;
    }
}
