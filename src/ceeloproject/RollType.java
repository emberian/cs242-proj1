/**
 * RollType is an enumerator that functions as a means to return meaningful values for round results.
 * 
 * @author The Brickettes (Corey Richardson and Adam Kimball) CS242 Project #1
 * 10/30/13
 * Project 1
 */

package ceeloproject;

enum RollType {

    None,
    LowSeq,
    Point,
    Trips,
    HighSeq;

    @Override
    public String toString() {
        switch (this) {
            case None:
                return "None";
            case LowSeq:
                return "LowSeq";
            case Point:
                return "Point";
            case Trips:
                return "Trips";
            case HighSeq:
                return "HighSeq";
            default:
                throw new AssertionError("Non-exhaustive switch");
        }
    }
}