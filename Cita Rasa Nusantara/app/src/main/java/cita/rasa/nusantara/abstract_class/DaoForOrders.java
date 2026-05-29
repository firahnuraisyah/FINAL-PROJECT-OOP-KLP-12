package cita.rasa.nusantara.abstract_class;

import cita.rasa.nusantara.models.Order;
import cita.rasa.nusantara.models.OrderItem;

public abstract class DaoForOrders {
    public abstract int saveOrder(Order order);

    public abstract void saveOrderItem(OrderItem item);

    public abstract void setupTable();
}
