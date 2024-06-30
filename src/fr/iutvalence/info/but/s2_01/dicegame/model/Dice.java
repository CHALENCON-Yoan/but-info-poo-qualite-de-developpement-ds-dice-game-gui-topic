package fr.iutvalence.info.but.s2_01.dicegame.model;

import java.util.Random;

/**
 * A dice, with a given amount of faces, laid on a given face, that can be rolled again and again.
 *
 * Two dices are considered equal if they show same face value (whatever number of faces).
 */
public class Dice {

    /**
     * Default face count (value is 6).
     */
    public static final int DEFAULT_FACE_COUNT = 6;

    /**
     * Random number generator used for dice rolls.
     */
    private final Random rng;

    /**
     * Number of faces.
     */
    private final int faceCount;

    /**
     * Face value (face currently visible).
     */
    private int faceValue;

    /**
     * Creates (and rolls) a new dice instance, with a given number of faces.
     * If number of faces is invalid (less than  1), dice instance is created with default number of faces.
     *
     * @param faceCount number of faces
     */
    public Dice(int faceCount) {
        if (faceCount <= 0)
            this.faceCount = DEFAULT_FACE_COUNT;
        else
            this.faceCount = faceCount;

        this.rng = new Random();
        this.roll();
    }

    /**
     * Creates (and rolls) a new dice instance, with default number of faces.
     */
    public Dice() {
        this(Dice.DEFAULT_FACE_COUNT);
    }

    /**
     * Returns face count.
     *
     * @return face count
     */
    public int getFaceCount() {
        return this.faceCount;
    }

    /**
     * Returns face value.
     *
     * @return value of currently visible face
     */
    public int getFaceValue() {
        return this.faceValue;
    }

    /**
     * Rolls the dice (randomly choosing a face)
     */
    public void roll() {
        this.faceValue = this.rng.nextInt(this.faceCount) + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dice dice = (Dice) o;

        return faceValue == dice.faceValue;
    }

    @Override
    public int hashCode() {
        return faceValue;
    }

    @Override
    public String toString() {
        return "Dice -> " + this.faceValue;
    }
}
