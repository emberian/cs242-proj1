/**
 * BaseController is a class that controls animation and text output to the
 * display.
 *
 * @author The Brickettes (Corey Richardson and Adam Kimball) CS 242 Project #1
 *
 */
package ceeloproject;

import dice.Die;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BaseController implements Initializable {

    @FXML
    private DiceBox p1dice;
    @FXML
    private DiceBox p2dice;
    @FXML
    private Label status;
    @FXML
    private Label p1;
    @FXML
    private Label p2;
    @FXML
    private Button rollbutton;

    final private Ceelo g = new Ceelo();
    private boolean roundinit = true;
    private boolean roundnum = true;

    @FXML
    private void rollDice(ActionEvent event) {
        final String res = g.playRound();
        resetRollAnimation();
        // Ghetto timer.
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { /* harmless */ }
                Platform.runLater(new Thread() {
                    @Override
                    public void run() {
                        setImagesToRollResult();
                        status.setText(res);
                        p1.setText("Human One        Rounds Won: " + g.getP1RoundsWon());
                        p2.setText("Human Two        Rounds Won: " + g.getP2RoundsWon());
                        rollbutton.setDisable(false);
                    }
                });
            }
        };
        rollbutton.setDisable(true);
        t.start();
        // Log the response for later debugging
        Logger.dlog(res.replaceAll("(\t|\n|\r)", "") + System.lineSeparator());

        Logger.dlog(System.lineSeparator() + "------------------------------" + System.lineSeparator() + System.lineSeparator());
    }

    /**
     * This initializes the file logger.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Logger.dlog("----- Initializing Ceelo -----" + System.lineSeparator() + System.lineSeparator());
        resetRollAnimation();
    }

    /**
     * This controls the actual animations of the dice.
     */
    public void resetRollAnimation() {
        Die[][] ds = g.getPlayerDice();
        if (roundinit == false) {
            if (roundnum) {
                p1dice.setImage(1, "/ceeloproject/roll1p1.gif");
                p1dice.setImage(2, "/ceeloproject/roll2p1.gif");
                p1dice.setImage(3, "/ceeloproject/roll3p1.gif");
                p2dice.setImage(1, "/ceeloproject/roll1p2.gif");
                p2dice.setImage(2, "/ceeloproject/roll2p2.gif");
                p2dice.setImage(3, "/ceeloproject/roll3p2.gif");
                roundnum = false;
            }
            if (g.getP1AnimType()) {
                p1dice.setImage(1, "/ceeloproject/roll1p1.gif");
                p1dice.setImage(2, "/ceeloproject/roll2p1.gif");
                p1dice.setImage(3, "/ceeloproject/roll3p1.gif");
            }
            if (g.getP2AnimType()) {
                p2dice.setImage(1, "/ceeloproject/roll1p2.gif");
                p2dice.setImage(2, "/ceeloproject/roll2p2.gif");
                p2dice.setImage(3, "/ceeloproject/roll3p2.gif");
            }
        }
        if (roundinit == true) {
            p1dice.setImage(1, "/ceeloproject/DieBlankp1.png");
            p1dice.setImage(2, "/ceeloproject/DieBlankp1.png");
            p1dice.setImage(3, "/ceeloproject/DieBlankp1.png");
            p2dice.setImage(1, "/ceeloproject/DieBlankp2.png");
            p2dice.setImage(2, "/ceeloproject/DieBlankp2.png");
            p2dice.setImage(3, "/ceeloproject/DieBlankp2.png");
            roundinit = false;

        }

    }

    /**
     * This sets dice images to reflect the value of the top of each die.
     */
    public void setImagesToRollResult() {
        Die[][] ds = g.getPlayerDice();
        p1dice.setImage(1, "/ceeloproject/Die" + ds[0][0].getTop() + "p1.png");
        p1dice.setImage(2, "/ceeloproject/Die" + ds[0][1].getTop() + "p1.png");
        p1dice.setImage(3, "/ceeloproject/Die" + ds[0][2].getTop() + "p1.png");
        p2dice.setImage(1, "/ceeloproject/Die" + ds[1][0].getTop() + "p2.png");
        p2dice.setImage(2, "/ceeloproject/Die" + ds[1][1].getTop() + "p2.png");
        p2dice.setImage(3, "/ceeloproject/Die" + ds[1][2].getTop() + "p2.png");
    }
}
