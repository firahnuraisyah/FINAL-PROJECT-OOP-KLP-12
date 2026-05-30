package cita.rasa.nusantara.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CategoryCard extends VBox {
    public CategoryCard(String name, Runnable onAction) {
        this.getStyleClass().add("category-card");
        this.setAlignment(Pos.CENTER);
        this.setMinWidth(100);
        this.setMinHeight(40);

        Label label = new Label(name);
        label.getStyleClass().add("category-label");
        
        this.getChildren().add(label);
        this.setOnMouseClicked(e -> onAction.run());
    }
}
