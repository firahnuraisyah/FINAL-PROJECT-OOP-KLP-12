package cita.rasa.nusantara.abstract_class;

import citarasa.models.CartItem;
import java.util.List;

public abstract class DaoForCart {
    public abstract List<CartItem> getCartItems();

    public abstract void addToCart(int foodId, int quantity);

    public abstract void updateCart(int cartId, int quantity);

    public abstract void deleteCart(int cartId);

    public abstract void clearCart();

    public abstract void setupTable();
}
