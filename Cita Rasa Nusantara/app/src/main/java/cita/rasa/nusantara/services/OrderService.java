package cita.rasa.nusantara.services;

import cita.rasa.nusantara.abstract_class.Customer;
import cita.rasa.nusantara.dao.CartDao;
import cita.rasa.nusantara.dao.OrderDao;
import cita.rasa.nusantara.models.CartItem;
import cita.rasa.nusantara.models.OrderItem;
import cita.rasa.nusantara.models.Order;
import java.util.Date;
import java.util.List;

public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private CartDao cartDao = new CartDao();
    private TableService tableService = new TableService();

    public int checkout(String name, String customerType, String tableNumber) {
        double subtotal = cartDao.getCartItems().stream().mapToDouble(CartItem::getTotalPrice).sum();
    
        Customer customer;
        if (customerType.equals("Member")) {
            customer = new cita.rasa.nusantara.models.MemberCustomer(name);
        } else {
            customer = new cita.rasa.nusantara.models.RegularCustomer(name);
        }

        double discount = customer.calculateDiscount(subtotal);
        double finalAmount = subtotal - discount;

        Order order = new Order(0, name, finalAmount, customerType, new Date());
        
        int orderId = orderDao.saveOrder(order); 

        if (orderId != -1) {
            List<CartItem> cartItems = cartDao.getCartItems();
            for (CartItem item : cartItems) {
                orderDao.saveOrderItem(new OrderItem(0, orderId, item.getFood(), item.getQuantity(), item.getFood().getPrice()));
            }
            
            tableService.occupyTable(tableNumber);
            return orderId;
        }
        
        return -1;
    }
}