package cita.rasa.nusantara.controllers;

import cita.rasa.nusantara.services.AuthService;
import cita.rasa.nusantara.scene.ShowScene;
import cita.rasa.nusantara.utils.AlertUtil;

public class LoginController {
    private AuthService authService = new AuthService();

    public void handleLogin(String username, String password) {
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            AlertUtil.showError("Login Gagal", "Username dan Password tidak boleh kosong!");
            return;
        }

        boolean success = authService.login(username, password);

        if (success) {
            AlertUtil.showInfo("Login Berhasil", "Selamat Datang di Cita Rasa Nusantara, " + username + "!");
            ShowScene.toHome();
        } else {
            AlertUtil.showError("Login Gagal", "Username atau Password salah.");
        }
    }
}