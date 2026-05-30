package cita.rasa.nusantara;

import cita.rasa.nusantara.scene.ShowScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CITA RASA NUSANTARA");
        
        ShowScene.setStage(primaryStage);
    
        primaryStage.setMaximized(true);
    
        ShowScene.toLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
