package citarasa.scene;

import citarasa.controllers.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginScene {
    private Scene scene;
    private LoginController loginController = new LoginController();

    public LoginScene() {
      
        VBox root = new VBox();
        root.getStyleClass().add("main-container");
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);
        root.setPadding(new Insets(30));

       
        Label lblTitle = new Label("CITA RASA NUSANTARA");
        lblTitle.getStyleClass().add("header-title");

        Label lblSubtitle = new Label("Silakan masuk ke akun Anda");
        lblSubtitle.getStyleClass().add("header-tagline");

       
        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Username");
        txtUsername.getStyleClass().add("custom-textfield");
        txtUsername.setMaxWidth(300);

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Password");
        txtPassword.getStyleClass().add("custom-textfield");
        txtPassword.setMaxWidth(300);

       
        Button btnLogin = new Button("MASUK");
        btnLogin.getStyleClass().add("btn-checkout");
        btnLogin.setMaxWidth(300);

       
        btnLogin.setOnAction(e -> {
            loginController.handleLogin(txtUsername.getText(), txtPassword.getText());
        });

        
        root.getChildren().addAll(lblTitle, lblSubtitle, txtUsername, txtPassword, btnLogin);

        
        this.scene = new Scene(root, 400, 500);
        

        if (getClass().getResource("/style.css") != null) {
            this.scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        }
    }

    public Scene getScene() {
        return this.scene;
    }
}