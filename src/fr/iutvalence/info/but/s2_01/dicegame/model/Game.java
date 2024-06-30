package fr.iutvalence.info.but.s2_01.dicegame.model;

/**
 * Dice game.
 */
public class Game {

    /**
     * Turns count (value is 10).
     */
    public static final int TURNS_COUNT = 10;

    /**
     * Winning sum (value is 7).
     */
    public static final int WINNING_SUM = 7;

    /**
     * Gain when roll achieves winning sum (value is 10).
     */
    public static final int GAIN = 10;

    /**
     * Dices.
     */
    private final Dice[] dices;

    /**
     * Turn number (starts at 0).
     */
    private int turnNumber;

    /**
     * Points.
     */
    private int points;

    /**
     * Creates a new game instance (initializes dices, turn number and points).
     *
     */
    public Game() {
        this.dices = new Dice[]{new Dice(), new Dice()};
        this.turnNumber = 0;
        this.points = 0;
    }

    /**
     * Returns points.
     *
     * @return points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Checks if game is ended.
     *
     * @return <i>true</i> if game is ended, <i>false</i> else
     */
    public boolean isEnded() {
        return this.turnNumber >= Game.TURNS_COUNT;
    }

    /**
     * Returns remaining turns.
     *
     * @return remaining turns
     */
    public int getRemainingTurns() {
        return Game.TURNS_COUNT - this.turnNumber;
    }

    /**
     * Plays next turn
     * (rolls dice, updates points if needed, creates and returns game turn info).
     *
     * @return game turn info
     */
    public GameTurnInfo playNextTurn() {
        this.rollDices();

        if (this.dices[0].getFaceValue() + this.dices[1].getFaceValue() == Game.WINNING_SUM)
            this.points += Game.GAIN;

        int[] diceFaceValues = new int[]{this.dices[0].getFaceValue(), this.dices[1].getFaceValue()};
        this.turnNumber++;
        return new GameTurnInfo(diceFaceValues, this.points);
    }

    /**
     * Rolls dices.
     */
    private void rollDices() {
        dices[0].roll();
        dices[1].roll();
    }
}
