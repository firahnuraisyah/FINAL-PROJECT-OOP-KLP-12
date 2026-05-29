package citarasa.scene;

import citarasa.components.Navbar;
import citarasa.controllers.CheckoutController;
import citarasa.services.CartService;
import citarasa.services.TableService;
import citarasa.utils.CurrencyUtil;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class CheckoutScene {
    private Scene scene;
    private CartService cartService = new CartService();
    private TableService tableService = new TableService();
    private CheckoutController checkoutController = new CheckoutController();

    public CheckoutScene() {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("main-container");

       
        VBox headerBox = new VBox();
        headerBox.getStyleClass().add("top-header");
        Label lblTitle = new Label("KONFIRMASI PESANAN MEJA");
        lblTitle.getStyleClass().add("header-title");
        headerBox.getChildren().add(lblTitle);
        root.setTop(headerBox);

      
        VBox formContainer = new VBox();
        formContainer.setPadding(new Insets(20));
        formContainer.setSpacing(20);
        formContainer.setAlignment(Pos.TOP_CENTER);

       
        VBox nameBox = new VBox(5);
        Label lblName = new Label("Nama Lengkap Pelanggan");
        lblName.getStyleClass().add("category-label");
        TextField txtName = new TextField();
        txtName.setPromptText("Masukkan nama Anda...");
        txtName.getStyleClass().add("custom-textfield");
        txtName.setMaxWidth(400);
        nameBox.getChildren().addAll(lblName, txtName);
        nameBox.setAlignment(Pos.TOP_LEFT);

       
        VBox customerBox = new VBox(5);
        Label lblCustomerType = new Label("Status Keanggotaan (Member / Regular)");
        lblCustomerType.getStyleClass().add("category-label");
        ComboBox<String> cmbCustomerType = new ComboBox<>();
        cmbCustomerType.setItems(FXCollections.observableArrayList("Regular", "Member"));
        cmbCustomerType.setValue("Regular"); 
        cmbCustomerType.getStyleClass().add("custom-combobox");
        cmbCustomerType.setMaxWidth(400);
        customerBox.getChildren().addAll(lblCustomerType, cmbCustomerType);

       
        VBox tableBox = new VBox(5);
        Label lblTable = new Label("Observasi Meja (Pilih Meja yang Tersedia)");
        lblTable.getStyleClass().add("category-label");
        ComboBox<String> cmbTable = new ComboBox<>();
        
       
        List<String> availableTables = tableService.getAvailableTables();
        cmbTable.setItems(FXCollections.observableArrayList(availableTables));
        if (!availableTables.isEmpty()) {
            cmbTable.setValue(availableTables.get(0)); 
        } else {
            cmbTable.setPromptText("Maaf, semua meja penuh");
        }
        cmbTable.getStyleClass().add("custom-combobox");
        cmbTable.setMaxWidth(400);
        tableBox.getChildren().addAll(lblTable, cmbTable);

        
        VBox inputWrapper = new VBox(15);
        inputWrapper.setMaxWidth(400);
        inputWrapper.getChildren().addAll(nameBox, customerBox, tableBox);
        formContainer.getChildren().add(inputWrapper);

        root.setCenter(formContainer);

       
        VBox bottomContainer = new VBox();
        bottomContainer.setSpacing(10);

        HBox summaryBox = new HBox();
        summaryBox.setPadding(new Insets(15, 20, 5, 20));
        summaryBox.setAlignment(Pos.CENTER_LEFT);

        Label lblTotalText = new Label("Total Belanja Sementara:");
        lblTotalText.getStyleClass().add("section-title");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

      
        Label lblTotalAmount = new Label(CurrencyUtil.formatIDR(cartService.calculateTotal()));
        lblTotalAmount.getStyleClass().add("total-price-large");

        summaryBox.getChildren().addAll(lblTotalText, spacer, lblTotalAmount);
        bottomContainer.getChildren().add(summaryBox);

       
        Button btnConfirm = new Button("KONFIRMASI PESANAN & AMBIL STRUK");
        btnConfirm.getStyleClass().add("btn-checkout");
        btnConfirm.setMaxWidth(Double.MAX_VALUE);
        
       
        btnConfirm.setOnAction(e -> {
            checkoutController.handleConfirmOrder(
                txtName.getText(),
                cmbCustomerType.getValue(),
                cmbTable.getValue()
            );
        });

        VBox btnWrapper = new VBox(btnConfirm);
        btnWrapper.setPadding(new Insets(0, 20, 10, 20));
        bottomContainer.getChildren().add(btnWrapper);

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