package citarasa.scene;

import citarasa.components.Navbar;
import citarasa.controllers.CartController;
import citarasa.models.CartItem;
import citarasa.services.CartService;
import citarasa.utils.CurrencyUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class CartScene {
    private Scene scene;
    private CartService cartService = new CartService();
    private CartController cartController = new CartController();

    public CartScene() {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("main-container");

       
        VBox headerBox = new VBox();
        headerBox.getStyleClass().add("top-header");
        
        Label lblTitle = new Label("KERANJANG BELANJA");
        lblTitle.getStyleClass().add("header-title");
        headerBox.getChildren().add(lblTitle);
        root.setTop(headerBox);

       
        VBox cartItemsContainer = new VBox();
        cartItemsContainer.setPadding(new Insets(20));
        cartItemsContainer.setSpacing(15);

        List<CartItem> cartList = cartService.getCartItems();

        if (cartList.isEmpty()) {
            Label lblEmpty = new Label("Keranjang Anda masih kosong. Silakan pilih menu di Beranda.");
            lblEmpty.getStyleClass().add("food-desc");
            cartItemsContainer.getChildren().add(lblEmpty);
        } else {
            for (CartItem item : cartList) {
                HBox itemCard = new HBox();
                itemCard.getStyleClass().add("cart-card");
                itemCard.setAlignment(Pos.CENTER_LEFT);
                itemCard.setPadding(new Insets(15));
                itemCard.setSpacing(15);

               
                VBox infoBox = new VBox();
                Label lblName = new Label(item.getFood().getName());
                lblName.getStyleClass().add("food-name");
                Label lblPrice = new Label(CurrencyUtil.formatIDR(item.getFood().getPrice()));
                lblPrice.getStyleClass().add("food-city");
                infoBox.getChildren().addAll(lblName, lblPrice);

               
                HBox qtyBox = new HBox();
                qtyBox.setAlignment(Pos.CENTER);
                qtyBox.setSpacing(10);

                Button btnMinus = new Button("-");
                btnMinus.getStyleClass().add("nav-button");
                btnMinus.setOnAction(e -> {
                    cartController.handleDecreaseQuantity(item.getId());
                    ShowScene.toCart(); 
                });

                Label lblQty = new Label(String.valueOf(item.getQuantity()));
                lblQty.getStyleClass().add("category-label");

                Button btnPlus = new Button("+");
                btnPlus.getStyleClass().add("nav-button");
                btnPlus.setOnAction(e -> {
                    cartController.handleIncreaseQuantity(item.getId());
                    ShowScene.toCart(); // Muat ulang halaman
                });

                qtyBox.getChildren().addAll(btnMinus, lblQty, btnPlus);

               
                Label lblSubtotal = new Label(CurrencyUtil.formatIDR(item.getTotalPrice()));
                lblSubtotal.getStyleClass().add("food-price");

              
                HBox spacer = new HBox();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                itemCard.getChildren().addAll(infoBox, spacer, qtyBox, lblSubtotal);
                cartItemsContainer.getChildren().add(itemCard);
            }
        }

        ScrollPane scrollPane = new ScrollPane(cartItemsContainer);
        scrollPane.getStyleClass().add("food-scroll");
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);

       
        VBox bottomContainer = new VBox();
        bottomContainer.setSpacing(10);

        if (!cartList.isEmpty()) {
            HBox summaryBox = new HBox();
            summaryBox.setPadding(new Insets(15, 20, 5, 20));
            summaryBox.setAlignment(Pos.CENTER_LEFT);

            Label lblTotalText = new Label("Total Pembayaran:");
            lblTotalText.getStyleClass().add("section-title");

            HBox spacerSummary = new HBox();
            HBox.setHgrow(spacerSummary, Priority.ALWAYS);

            Label lblTotalAmount = new Label(CurrencyUtil.formatIDR(cartService.calculateTotal()));
            lblTotalAmount.getStyleClass().add("total-price-large");

            summaryBox.getChildren().addAll(lblTotalText, spacerSummary, lblTotalAmount);
            bottomContainer.getChildren().add(summaryBox);

            Button btnCheckout = new Button("LANJUT KE CHECKOUT MEJA");
            btnCheckout.getStyleClass().add("btn-checkout");
            btnCheckout.setMaxWidth(Double.MAX_VALUE);
            btnCheckout.setOnAction(e -> ShowScene.toCheckout());
            
            VBox btnWrapper = new VBox(btnCheckout);
            btnWrapper.setPadding(new Insets(0, 20, 10, 20));
            bottomContainer.getChildren().add(btnWrapper);
        }

        Navbar navbar = new Navbar();
        bottomContainer.getChildren().add(navbar);
        root.setBottom(bottomContainer);

       
        this.scene = new Scene(root, 1024, 768);

        if (getClass().getResource("/style.css") != null) {
            this.scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        }
    }

    public Scene getScene() {
        return this.scene;
    }
}