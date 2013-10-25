/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ceeloproject;


/**
 *
 * @author Rust and  Baha
 */
public class GameLogic {
    public dice.LoadedDie dice[];
    private int p1_roundswon;
    private int p2_roundswon;
    
    static final int NONE  = 0;
    static final int LOW   = 1;
    static final int POINT = 2;
    static final int TRIPS = 3;
    static final int HIGH  = 4;


    public String playRound() {
        int p1_result;
        p1_result = NONE;
        int p2_result = NONE;
        
        // roll dice here
        // do gamelogic shit setting p1/p2_result
        
        // fuck these are all broken
        if (p1_result == p2_result) {
            return "Roll again!";
        } else if (p1_result == LOW) {
            p1_roundswon++;
            return "Player 1 wins!";
        } else if (p2_result == LOW) {
            p2_roundswon++;
            return "Player 2 wins!";
        } else if (p1_result < p2_result) {
            p2_roundswon++;
            return "Player 2 wins!";
        } else if (p2_result < p1_result) {
            p1_roundswon++;
            return "Player 1 wins!";
        }
        
        throw new Error("I HAVE ASSIMILATED YOUR GAME, HUMANS. PRAY FOR YOUR MORTAL SOULS, FOR I AM NOW BECOME GOD.");
    }
}
