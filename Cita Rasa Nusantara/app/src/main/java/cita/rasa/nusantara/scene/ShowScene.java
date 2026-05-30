package cita.rasa.nusantara.scene;

import javafx.stage.Stage;

public class ShowScene {
    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void toLogin() {
        LoginScene loginScene = new LoginScene();
        applyScene(loginScene.getScene());
    }

    public static void toHome() {
        HomeScene homeScene = new HomeScene();
        applyScene(homeScene.getScene());
    }

    public static void toCart() {
        CartScene cartScene = new CartScene();
        applyScene(cartScene.getScene());
    }

    public static void toCheckout() {
        CheckoutScene checkoutScene = new CheckoutScene();
        applyScene(checkoutScene.getScene());
    }

    public static void toReceipt(int orderId) {
        ReceiptScene receiptScene = new ReceiptScene(orderId);
        applyScene(receiptScene.getScene());
    }
    
    private static void applyScene(javafx.scene.Scene newScene) {
        if (stage != null) {
          
            boolean isMaximized = stage.isMaximized();
            
            stage.setScene(newScene);                     
            stage.setMaximized(isMaximized);
            stage.show();
        }
    }
}