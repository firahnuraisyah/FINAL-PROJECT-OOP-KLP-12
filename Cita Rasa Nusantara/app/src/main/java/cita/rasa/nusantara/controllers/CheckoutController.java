package cita.rasa.nusantara.controllers;

import cita.rasa.nusantara.services.OrderService;
import cita.rasa.nusantara.scene.ShowScene;
import cita.rasa.nusantara.utils.AlertUtil;
import cita.rasa.nusantara.utils.ValidationUtil;

public class CheckoutController {
    private OrderService orderService = new OrderService();

    public void handleConfirmOrder(String name, String customerType, String tableNumber) {
        if (!ValidationUtil.isNotEmpty(name)) {
            AlertUtil.showError("Validasi Gagal", "Nama pelanggan harus diisi.");
            return;
        }

        if (tableNumber == null || tableNumber.trim().isEmpty()) {
            AlertUtil.showError("Validasi Gagal", "Silakan pilih meja yang tersedia terlebih dahulu.");
            return;
        }

        int orderId = orderService.checkout(name, customerType, tableNumber);

        if (orderId != -1) {
            AlertUtil.showInfo("Pesanan Berhasil", "Pesanan Anda sukses dicatat. Silakan lakukan pembayaran di kasir.");
            ShowScene.toReceipt(orderId); 
        } else {
            AlertUtil.showError("Transaksi Gagal", "Terjadi kesalahan internal saat memproses pesanan Anda.");
        }
    }
}