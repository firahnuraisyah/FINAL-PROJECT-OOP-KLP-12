package cita.rasa.nusantara.scene;

import cita.rasa.nusantara.components.Navbar;
import cita.rasa.nusantara.services.CartService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ReceiptScene {
    private Scene scene;
    private CartService cartService = new CartService();
    
    public ReceiptScene(int orderId) {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("main-container");
        
        VBox receiptContainer = new VBox();
        receiptContainer.setPadding(new Insets(40, 20, 40, 20));
        receiptContainer.setAlignment(Pos.CENTER);

        VBox receiptBox = new VBox();
        receiptBox.getStyleClass().add("receipt-box");
        receiptBox.setSpacing(15);
        receiptBox.setMaxWidth(450);
        receiptBox.setPadding(new Insets(30));
        receiptBox.setAlignment(Pos.TOP_CENTER);
       
        Label lblSuccess = new Label("✓ PESANAN BERHASIL DICATAT");
        lblSuccess.getStyleClass().add("food-price"); 
        lblSuccess.setStyle("-fx-font-size: 18px; -fx-font-weight: 900;");

        Label lblInvoice = new Label("ID Transaksi: #CN-" + orderId);
        lblInvoice.getStyleClass().add("food-city");

        Label lblLine = new Label("--------------------------------------------");
        lblLine.setStyle("-fx-text-fill: #E2D6CA;");
       
        Label lblInstructionTitle = new Label("PETUNJUK PEMBAYARAN KASIR");
        lblInstructionTitle.getStyleClass().add("category-label");

        Label lblInstructionBody = new Label(
            "1. Harap bawa atau ingat ID Transaksi Anda di atas.\n" +
            "2. Sebutkan nama Anda kepada petugas Kasir utama.\n" +
            "3. Lakukan pembayaran fisik di kasir sesuai nominal.\n" +
            "4. Petugas akan menyerahkan nomor meja fisik Anda."
        );
        lblInstructionBody.getStyleClass().add("food-desc");
        lblInstructionBody.setWrapText(true);
        lblInstructionBody.setStyle("-fx-font-size: 13px; -fx-line-spacing: 5px;");
       
        Button btnDone = new Button("SELESAI & KEMBALI KEBENRANDA");
        btnDone.getStyleClass().add("btn-checkout");
        btnDone.setMaxWidth(Double.MAX_VALUE);
        btnDone.setOnAction(e -> {
            cartService.clearCart(); 
            ShowScene.toHome();
        });
        
        receiptBox.getChildren().addAll(
            lblSuccess, 
            lblInvoice, 
            lblLine, 
            lblInstructionTitle, 
            lblInstructionBody, 
            lblLine, 
            btnDone
        );
        
        receiptContainer.getChildren().add(receiptBox);
        root.setCenter(receiptContainer);
        
        Navbar navbar = new Navbar();
        root.setBottom(navbar);
       
        this.scene = new Scene(root, 1024, 768);

        if (getClass().getResource("/style.css") != null) {
            this.scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        }
    }

    public Scene getScene() {
        return this.scene;
    }
}