/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ceeloproject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DiceBox extends HBox {
    @FXML private ImageView image1;
    @FXML private ImageView image2;
    @FXML private ImageView image3;
    
    public DiceBox() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DiceBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setImage(int image, String img) {
        if (image < 1 || image > 3) {
            throw new IllegalArgumentException("Image must be 1-3");
        }

        switch (image) {
            case 1:
                image1.setImage(new Image(img));
                return;
            case 2:
                image2.setImage(new Image(img));
                return;
            case 3:
                image3.setImage(new Image(img));
                return;
            default:
                throw new AssertionError("Inexhaustive switch");
        }
        
    }

    @FXML
    protected void doSomething() {
        System.out.println("The button was clicked!");
    }
}