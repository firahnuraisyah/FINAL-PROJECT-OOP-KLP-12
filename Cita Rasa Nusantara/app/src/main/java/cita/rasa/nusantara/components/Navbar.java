package cita.rasa.nusantara.components;

import cita.rasa.nusantara.scene.ShowScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Navbar extends HBox {

    public Navbar() {
        this.getStyleClass().add("navbar");
        this.setAlignment(Pos.CENTER);
        this.setSpacing(40);

        Button btnHome = new Button("BERANDA");
        Button btnCart = new Button("KERANJANG");
        Button btnLogout = new Button("LOGOUT");

        btnHome.getStyleClass().add("nav-button");
        btnCart.getStyleClass().add("nav-button");
        btnLogout.getStyleClass().add("nav-button");

        btnHome.setOnAction(e -> ShowScene.toHome());
        btnCart.setOnAction(e -> ShowScene.toCart());
        btnLogout.setOnAction(e -> {
            ShowScene.toLogin(); 
        });

        this.getChildren().addAll(btnHome, btnCart, btnLogout);
    }
}