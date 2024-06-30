package fr.iutvalence.info.but.s2_01.dicegame.model;

/**
 * Game turn information (dice values, points).
 */
public class GameTurnInfo {

    /**
     * Dice face values.
     */
    private final int[] diceFaceValues;

    /**
     * Points.
     */
    private final int points;

    /**
     * Creates a new game turn information instance, with given dice values/points.
     *
     * @param diceFaceValues dices values
     * @param points          score
     */
    public GameTurnInfo(int[] diceFaceValues, int points) {
        this.diceFaceValues = diceFaceValues;
        this.points = points;
    }

    /**
     * Returns dice face value, for a given dice number.
     *
     * @param diceNumber dice number
     * @return given dice number face value
     * @throws InvalidDiceNumberException if dice number is invalid
     */
    public int getDiceFaceValue(int diceNumber) throws InvalidDiceNumberException {
        if (diceNumber > this.diceFaceValues.length)
            throw new InvalidDiceNumberException();
        return this.diceFaceValues[diceNumber];
    }

    /**
     * Returns points.
     *
     * @return points
     */
    public int getPoints() {
        return this.points;
    }
}
