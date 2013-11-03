/**
 * Ceelo.java contains core game logic, and functions as the controller for the game.
 *
 * @author The Brickettes (Corey Richardson and Adam Kimball) CS242 Project #1
 * 10/30/13
 * Project 1
 *
 */
package ceeloproject;

import dice.Die;


public class Ceelo {

    // todo: add a constructor that lets you pass in the players to use.
    private Player p1 = new Player(), p2 = new Player();
    private int p1_roundswon;
    private int p2_roundswon;
    private boolean p1AnimType = true;
    private boolean p2AnimType = true;
    private boolean rolled_yet = false;

    /**
     * Rolls both players.
     *
     * @return A string describing who won this round.
     */
    public String playRound() {
        p1AnimType = false;
        p2AnimType = false;

        if (!rolled_yet || (!p1.needsReroll() && !p2.needsReroll())) {
            rolled_yet = true;
            p1AnimType = true;
            p2AnimType = true;
            Logger.dlog("P1 Rolls: ");
            p1.roll();
            Logger.dlog("P2 Rolls: ");
            p2.roll();
        } else {
			if (p1.needsReroll()) {
                p1AnimType = true;
                Logger.dlog("P1 Rolls: ");
                p1.roll();
            }
            if (p2.needsReroll()) {
                p2AnimType = true;
                Logger.dlog("P2 Rolls: ");
                p2.roll();
            }
        }

        int result = p1.compareTo(p2);
        if (result < 0) {
            p2_roundswon++;
            return "Human two wins, thus demonstrating his superiority " + System.lineSeparator() + "\t\tin staring blankly at a display.";
        } else if (result == 0) {
            return "No human was the victor. Throw the dice once more.";
        } else {
            p1_roundswon++;
            return "\t\tHuman one has won the round." + System.lineSeparator() + " Perhaps human two should work the button next time?";
        }
    }

    /**
     * @return the number of rounds player 1 has won.
     */
    public int getP1RoundsWon() {
        return p1_roundswon;
    }

    /**
     * @return whether or not Player 1's Dice roll should be animated
     */
    public boolean getP1AnimType() {
        return p1AnimType;
    }

    /**
     * @return whether or not Player 2's Dice roll should be animated
     */
    public boolean getP2AnimType() {
        return p2AnimType;
    }

    /**
     * @return the number of rounds player 2 has won.
     */
    public int getP2RoundsWon() {
        return p2_roundswon;
    }

    /**
     * @return An array of {player1 dice, player2 dice}
     */
    public Die[][] getPlayerDice() {
        Die[][] ds = new Die[3][2];
        ds[0] = p1.getDice();
        ds[1] = p2.getDice();
        return ds;
    }
}
