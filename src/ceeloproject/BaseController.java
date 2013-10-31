package ceeloproject;

import dice.Die;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author csguest
 */
public class BaseController implements Initializable {

    @FXML
    private DiceBox p1dice;
    @FXML
    private DiceBox p2dice;
    @FXML
    private Label status;

    final private Ceelo g = new Ceelo();

    @FXML
    private void rollDice(ActionEvent event) {
        final String res = g.playRound();
        resetRollAnimation();

        // Ghetto timer.
        Thread t = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { /* harmless */ }

                Platform.runLater(new Thread() {
                    public void run() {
                        setImagesToRollResult();
                        status.setText(res);
                    }
                });
            }
        };
        t.start();
        // Log the response for later debugging
        Logger.dlog(res + System.lineSeparator());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Logger.dlog("----- starting ceelo -----" + System.lineSeparator());
        resetRollAnimation();
    }

    public void resetRollAnimation() {
        p1dice.setImage(1, "/ceeloproject/roll1p1.gif");
        p1dice.setImage(2, "/ceeloproject/roll2p1.gif");
        p1dice.setImage(3, "/ceeloproject/roll3p1.gif");
        p2dice.setImage(1, "/ceeloproject/roll1p2.gif");
        p2dice.setImage(2, "/ceeloproject/roll2p2.gif");
        p2dice.setImage(3, "/ceeloproject/roll3p2.gif");
    }

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
