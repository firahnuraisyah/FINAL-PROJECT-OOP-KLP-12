package citarasa.scene;

import citarasa.components.FoodCard;
import citarasa.components.Navbar;
import citarasa.dao.FoodDao;
import citarasa.models.Food;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class HomeScene {
    private Scene scene;
    private FoodDao foodDao = new FoodDao();
    private FlowPane foodGrid;

    public HomeScene() {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("main-container");

        
        VBox topContainer = new VBox();
        topContainer.setSpacing(10);

        VBox headerBox = new VBox();
        headerBox.getStyleClass().add("top-header");
        headerBox.setSpacing(5);

        Label lblTitle = new Label("CITA RASA NUSANTARA");
        lblTitle.getStyleClass().add("header-title");

        Label lblSubtitle = new Label("Jelajahi kelezatan kuliner otentik dari berbagai daerah");
        lblSubtitle.getStyleClass().add("header-tagline");

        headerBox.getChildren().addAll(lblTitle, lblSubtitle);

        // --- TOMBOL FILTER WILAYAH ---
        HBox filterBox = new HBox(10);
        filterBox.setPadding(new Insets(10, 20, 10, 20));
        filterBox.setAlignment(Pos.CENTER_LEFT);
        
        Button btnSemua = new Button("Semua");
        Button btnSumatra = new Button("Sumatra");
        Button btnJawa = new Button("Jawa");
        Button btnSulawesi = new Button("Sulawesi");
        Button btnBaliNT = new Button("Bali & NT");
        Button btnPapua = new Button("Papua");

        btnSemua.getStyleClass().add("button-filter");
        btnSumatra.getStyleClass().add("button-filter");
        btnJawa.getStyleClass().add("button-filter");
        btnSulawesi.getStyleClass().add("button-filter");
        btnBaliNT.getStyleClass().add("button-filter");
        btnPapua.getStyleClass().add("button-filter");

        filterBox.getChildren().addAll(btnSemua, btnSumatra, btnJawa, btnSulawesi, btnBaliNT, btnPapua);

        topContainer.getChildren().addAll(headerBox, filterBox);
        root.setTop(topContainer);

       
        foodGrid = new FlowPane();
        foodGrid.setPadding(new Insets(20));
        foodGrid.setHgap(20);
        foodGrid.setVgap(20);
        foodGrid.setAlignment(Pos.TOP_LEFT);

        loadMenuByRegion("Semua");

        btnSemua.setOnAction(e -> loadMenuByRegion("Semua"));
        btnSumatra.setOnAction(e -> loadMenuByRegion("Sumatra"));
        btnJawa.setOnAction(e -> loadMenuByRegion("Jawa"));
        btnSulawesi.setOnAction(e -> loadMenuByRegion("Sulawesi"));
        btnBaliNT.setOnAction(e -> loadMenuByRegion("Bali & Nusa Tenggara"));
        btnPapua.setOnAction(e -> loadMenuByRegion("Maluku & Papua"));

        ScrollPane scrollPane = new ScrollPane(foodGrid);
        scrollPane.getStyleClass().add("food-scroll");
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);

       
        Navbar navbar = new Navbar();
        root.setBottom(navbar);

        
        this.scene = new Scene(root);

        if (getClass().getResource("/style.css") != null) {
            this.scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        }
    }

    private void loadMenuByRegion(String region) {
        foodGrid.getChildren().clear();
        List<Food> filteredList;
        if (region.equals("Semua")) {
            filteredList = foodDao.getFoods();
        } else {
            filteredList = foodDao.getFoodsByIsland(region);
        }

        for (Food food : filteredList) {
            FoodCard card = new FoodCard(food);
            foodGrid.getChildren().add(card);
        }
    }

    public Scene getScene() {
        return this.scene;
    }
}