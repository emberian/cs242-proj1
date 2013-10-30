/*
    This file contains all of the game logic, presenting a simple OO interface.
*/

package ceeloproject;

import dice.Die;

enum RollType {
    None,
    LowSeq,
    Point,
    Trips,
    HighSeq
}

/**
 * A player.
 * 
 * A player in Cee-lo has three dice. To determine which player has won a round,
 * use the `compareTo` method. To roll a player's dice, use the `roll` method.
 * 
 * @author Corey Richardson
 */
class Player implements Comparable {
    // used for storing the roll result
    private RollType type;
    // used for storing the singleton/trips value
    private int extra_value;
    
    private Die[] dice;

    /**
     * Create a new player, randomizing the dice.
     */
    public Player() {
        dice = new Die[3];
        for (int i = 0; i < 3; i++) {
            dice[i] = new Die();
        }
        determine_result();
    }

    // TODO: constructor passing in the dice manually, which could be loaded
    // (needs to check that the array length is 3)\
    
    /**
     * Roll the dice.
     */
    public void roll() {
        for (Die d : dice) {
            d.roll();
            System.out.print(d.toString() + " ");
        }
        System.out.println();
        determine_result();
    }
    
    // Figure out which result the player currently has.
    private void determine_result() {
        int a, b, c;
        a = dice[0].getTop();
        b = dice[1].getTop();
        c = dice[2].getTop();

        if (a == b && b == c) {
            type = RollType.Trips;
            extra_value = a;
        } else if (a == b) {
            type = RollType.Point;
            extra_value = c;
        } else if (b == c) {
            type = RollType.Point;
            extra_value = a;
        } else if (a == c) {
            type = RollType.Point;
            extra_value = b;
        } else if ((a == 1 || b == 1 || c == 1) && (a == 2 || b == 2 || c == 2)
                   && (a == 3 || b == 3 || c == 3)) {
            type = RollType.LowSeq;
        } else if ((a == 4 || b == 4 || c == 4) && (a == 5 || b == 5 || c == 5)
                   && (a == 6 || b == 6 || c == 6)) {
            type = RollType.HighSeq;
        } else {
            type = RollType.None;
        }
    }
    
    /**
     * Determine which player has a winning hand.
     * 
     * @param o Player to compare to
     * @return -1 if this player is losing, 0 if there is a tie, 1 if this player is winning.
     */
    @Override
    public int compareTo(Object o) {
        Player p = (Player) o;
        
        if (type == p.type) {
            if (type == RollType.Point || type == RollType.Trips) {
                return Integer.compare(extra_value, p.extra_value);
            } else {
                return 0;
            }
        }
        
        if (type == RollType.LowSeq) {
            return -1;
        } else if (type == RollType.HighSeq) {
            return 1;
        } else {
            return 0;
        }
    }
}

/**
 * A game of Cee-lo.
 *
 * @author Corey Richardson
 */
public class Ceelo {
    // todo: add a constructor that lets you pass in the players to use.
    private Player p1 = new Player(), p2 = new Player();
    
    private int p1_roundswon;
    private int p2_roundswon;

    /**
     * Rolls both players.
     * 
     * @return A string describing who won this round.
     */
    public String playRound() {
        p1.roll();
        p2.roll();
        
        int result = p1.compareTo(p2);
        if (result < 0) {
            p2_roundswon++;
            return "Human number two wins, thus demonstrating his superiority in staring blankly at a display.";
        } else if (result == 0) {
           return "No human was the victor. Throw the dice once more.";
        } else {
            p1_roundswon++;
            return "Human one has won the round. Perhaps human two should work the button next time.";
        }
    }
    
    /**
     * @return the number of rounds player 1 has won.
     */
    public int getP1RoundsWon() {
        return p1_roundswon;
    }
    
    /**
     * @return the number of rounds player 2 has won.
     */
    public int getP2RoundsWon() {
        return p2_roundswon;
    }
}
