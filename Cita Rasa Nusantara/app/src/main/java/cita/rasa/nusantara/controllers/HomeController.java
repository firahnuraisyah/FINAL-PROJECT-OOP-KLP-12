package cita.rasa.nusantara.controllers;

import cita.rasa.nusantara.services.CartService;
import cita.rasa.nusantara.utils.AlertUtil;

public class HomeController {
    private CartService cartService = new CartService();

    public void handleAddFoodToCart(int foodId, String foodName) {
        try {
            cartService.addToCart(foodId);
            AlertUtil.showInfo("Keranjang Diperbarui", foodName + " berhasil ditambahkan ke keranjang belanja.");
        } catch (Exception e) {
            AlertUtil.showError("Gagal", "Gagal menambahkan item ke keranjang: " + e.getMessage());
        }
    }
}