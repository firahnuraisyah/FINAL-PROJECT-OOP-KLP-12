package cita.rasa.nusantara.components;

import cita.rasa.nusantara.controllers.HomeController;
import cita.rasa.nusantara.models.Food;
import cita.rasa.nusantara.utils.CurrencyUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FoodCard extends VBox {
    private HomeController homeController = new HomeController();

    public FoodCard(Food food) {
        this.getStyleClass().add("food-card");
        this.setSpacing(8);
        this.setPrefWidth(300);

        VBox contentBox = new VBox();
        contentBox.getStyleClass().add("food-content");
        contentBox.setSpacing(5);
        contentBox.setPadding(new Insets(10, 15, 15, 15));

        Label lblName = new Label(food.getName());
        lblName.getStyleClass().add("food-name");

        Label lblCity = new Label(food.getCity() + " (" + food.getIsland() + ")");
        lblCity.getStyleClass().add("food-city");

        Label lblDesc = new Label(food.getDescription());
        lblDesc.getStyleClass().add("food-desc");
        lblDesc.setWrapText(true);
        lblDesc.setMinHeight(40); 

        HBox actionBox = new HBox();
        actionBox.setAlignment(Pos.CENTER_LEFT);
        actionBox.setSpacing(10);

        Label lblPrice = new Label(CurrencyUtil.formatIDR(food.getPrice()));
        lblPrice.getStyleClass().add("food-price");
        
        VBox.setVgrow(actionBox, javafx.scene.layout.Priority.ALWAYS);
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        Button btnAdd = new Button("+");
        btnAdd.getStyleClass().add("btn-add");

        btnAdd.setOnAction(e -> {
            homeController.handleAddFoodToCart(food.getId(), food.getName());
        });

        actionBox.getChildren().addAll(lblPrice, spacer, btnAdd);
        contentBox.getChildren().addAll(lblName, lblCity, lblDesc, actionBox);
        
        this.getChildren().add(contentBox);
    }
}