package jeu2048;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stagePrincipal;

    public static Stage getStagePrincipal(){
        return stagePrincipal;
    }
    @Override
    public void start(Stage stage){
        stagePrincipal = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("accueil.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("TEST!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }


    }

    public static void main(String[] args) {
        launch();
    }


}

