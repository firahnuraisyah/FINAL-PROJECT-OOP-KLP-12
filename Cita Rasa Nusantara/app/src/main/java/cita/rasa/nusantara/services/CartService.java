package cita.rasa.nusantara.services;

import cita.rasa.nusantara.dao.CartDao;
import cita.rasa.nusantara.models.CartItem;
import java.util.List;

public class CartService {
    private CartDao cartDao = new CartDao();

    public List<CartItem> getCartItems() {
        return cartDao.getCartItems();
    }

    public void addToCart(int foodId) {
        List<CartItem> items = cartDao.getCartItems();
        boolean itemExists = false;

        for (CartItem item : items) {
            if (item.getFood().getId() == foodId) {
                cartDao.updateCart(item.getId(), item.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            cartDao.addToCart(foodId, 1);
        }
    }

    public void updateQuantity(int cartItemId, int newQuantity) {
        if (newQuantity <= 0) {
            cartDao.deleteCart(cartItemId);
        } else {
            cartDao.updateCart(cartItemId, newQuantity);
        }
    }

    public void clearCart() {
        cartDao.clearCart();
    }

    public double calculateTotal() {
        return cartDao.getCartItems().stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}