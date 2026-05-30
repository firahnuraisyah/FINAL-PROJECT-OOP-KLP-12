package cita.rasa.nusantara.controllers;

import cita.rasa.nusantara.services.CartService;

public class CartController {
    private CartService cartService = new CartService();

    public void handleIncreaseQuantity(int cartItemId) {
        cartService.getCartItems().stream()
                .filter(item -> item.getId() == cartItemId)
                .findFirst()
                .ifPresent(item -> cartService.updateQuantity(cartItemId, item.getQuantity() + 1));
    }

    public void handleDecreaseQuantity(int cartItemId) {
        cartService.getCartItems().stream()
                .filter(item -> item.getId() == cartItemId)
                .findFirst()
                .ifPresent(item -> cartService.updateQuantity(cartItemId, item.getQuantity() - 1));
    }
}