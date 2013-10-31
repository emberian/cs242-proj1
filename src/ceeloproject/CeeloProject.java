package ceeloproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import dice.*;

/**
 * This is the main class for the Ceelo game. This kicks off the FXML loader,
 * sets the stage, and sets the title bar image/text.
 *
 * @author The Brickettes (Corey Richardson and Adam Kimball
 * CS242
 * Project #1
 * 10/30/13
 * 
 */
public class CeeloProject extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("A Game of Dice");
        stage.getIcons().add(new Image("/ceeloproject/Die5p1.png"));
        Parent root = FXMLLoader.load(getClass().getResource("Base.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored. It simply runs start();
     * @param args (command line arguments)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
