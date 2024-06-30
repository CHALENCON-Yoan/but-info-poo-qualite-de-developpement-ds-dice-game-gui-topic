package fr.iutvalence.info.but.s2_01.dicegame.model;

import java.util.List;

/**
 * General contract for game Man-Machine-Interface.
 */
public interface Mmi {

    /**
     * Displays score history.
     *
     * @param scores scores
     */
    void displayHighScores(List<Score> scores);

    /**
     * Notifies that game has just started.
     */
    void notifyGameIsStarted();

    /**
     * Notifies that a new turn is about to be played.
     *
     * @param remainingTurns remaining turns.
     * @param score          score.
     */
    void notifyNewTurn(int remainingTurns, int score);

    /**
     * Notifies that player has to trigger roll.
     */
    void notifyWaitForRoll();

    /**
     * Notifies roll results.
     *
     * @param gameTurnInfo game turn info, containing roll results.
     */
    void notifyRollResult(GameTurnInfo gameTurnInfo);

    /**
     * Notifies that game has just ended.
     *
     * @param score score
     */
    void notifyGameHasEnded(int score);

    /**
     * Asks for choice (play game, view scores, exit)
     *
     * @return choice
     */
    Choice askForChoice();

    /**
     * Asks for username.
     *
     * @return username
     */
    String askForUserName();

    /**
     * Notifies that score history is broken (unable to read or write).
     */
    void notifyScoreHistoryIsBroken();
}
