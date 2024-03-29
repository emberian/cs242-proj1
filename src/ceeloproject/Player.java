/**
 * A player in Cee-lo has three dice. To determine which player has won a round,
 * use the `compareTo` method. To roll a player's dice, use the `roll` method.
 *
 * @author The Brickettes (Corey Richardson and Adam Kimball) CS242 Project #1
 * 10/30/13 Project 1
 */
package ceeloproject;

import dice.Die;

// todo: split Player into public class?
class Player implements Comparable, Cloneable {

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
    // (needs to check that the array length is 3)
    /**
     * Roll the dice.
     */
    public void roll() {
        for (Die d : dice) {
            d.roll();
            Logger.dlog(d.toString() + ", ");
        }
        Logger.dlog(System.lineSeparator());
        determine_result();
        Logger.dlog("Result: extra_value=" + extra_value + ", type=" + type.toString() + System.lineSeparator() + System.lineSeparator());
    }

    /**
     * Get a copy of the internal dice.
     */
    public Die[] getDice() {
        Die[] ds = new Die[3];
        for (int i = 0; i < 3; i++) {
            ds[i] = new Die(dice[i]);
        }
        return ds;
    }

    /**
     * Check if the player needs to re-roll in case of a meaningless combination
     */
    public boolean needsReroll() {
        return type == RollType.None;
    }

    // Figure out which result the player currently has.
    private void determine_result() {
        this.type = RollType.None;

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
     * @return -1 if this player is losing, 0 if there is a tie, 1 if this
     * player is winning.
     */
    @Override
    public int compareTo(Object o) {
        Player p = (Player) o;

        if (type == RollType.None || p.type == RollType.None) {
            return 0;
        }

        if (type == p.type) {
            if (type == RollType.Point || type == RollType.Trips) {
                return Integer.compare(extra_value, p.extra_value);
            } else {
                return 0;
            }
        }

        if (type == RollType.Point && p.type == RollType.Trips) {
            return -1;
        }

        if (type == RollType.Trips && p.type == RollType.Point) {
            return 1;
        }

        if (type == RollType.LowSeq || p.type == RollType.HighSeq) {
            return -1;
        } else if (type == RollType.HighSeq || p.type == RollType.LowSeq) {
            return 1;
        } else {
            return 0;
        }
    }
}
