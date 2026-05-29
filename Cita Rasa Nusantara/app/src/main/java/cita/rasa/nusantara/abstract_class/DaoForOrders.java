package cita.rasa.nusantara.abstract_class;

import citarasa.models.Order;
import citarasa.models.OrderItem;

public abstract class DaoForOrders {
    public abstract int saveOrder(Order order);

    public abstract void saveOrderItem(OrderItem item);

    public abstract void setupTable();
}
