/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ceeloproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author csguest
 */
public class BaseController implements Initializable {
    private GameLogic g = new GameLogic();
    
    @FXML
    private void rollDice(ActionEvent event) {
        String res = g.playRound();
        // animate
        // set dice labels from g.dice
        // set result label
        System.out.println(res);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
