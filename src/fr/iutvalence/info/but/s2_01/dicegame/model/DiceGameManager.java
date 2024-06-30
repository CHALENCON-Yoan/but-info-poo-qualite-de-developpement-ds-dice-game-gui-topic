package fr.iutvalence.info.but.s2_01.dicegame.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dice game manager, in charge of orchestrating application according to user choices.
 */
public class DiceGameManager {

    /**
     * High scores table size (value is 10).
     */
    public static final int HIGH_SCORES_COUNT = 10;

    /**
     * Score history.
     */
    private final ScoreHistoryService scoreHistory;

    /**
     * MMI.
     */
    private final Mmi mmi;

    /**
     * Creates a new dice game manager instance, with given score history and mmi.
     *
     * @param scoreHistory score history
     * @param mmi          mmi
     */
    public DiceGameManager(ScoreHistoryService scoreHistory, Mmi mmi) {
        this.scoreHistory = scoreHistory;
        this.mmi = mmi;
    }

    /**
     * Starts dice game manager
     * (endlessly asking user for a choice and processing corresponding action).
     */
    public void start() {
        while (true) {
            switch (this.mmi.askForChoice()) {
                case PLAY_NEW_GAME:
                    String userName = this.mmi.askForUserName();
                    try {
                        this.playNewGame(userName);
                    } catch (IOException e) {
                       this.mmi.notifyScoreHistoryIsBroken();
                    }
                    break;
                case HIGH_SCORES:
                    this.displayHighScores();
                    break;
                case EXIT:
                default:
                    return;
            }
        }
    }

    /**
     * Displays high scores
     * (only the first HIGH_SCORES_COUNT scores are displayed, in descending point order).
     */
    private void displayHighScores() {
        List<Score> scores = this.scoreHistory.getScores();
        Collections.sort(scores);
        Collections.reverse(scores);
        List<Score> highScores = new ArrayList<Score>();
        int limit = scores.size() < DiceGameManager.HIGH_SCORES_COUNT ? scores.size() : DiceGameManager.HIGH_SCORES_COUNT;
        for (int index = 0; index < limit; index++)
            highScores.add(scores.get(index));
        this.mmi.displayHighScores(highScores);
    }

    /**
     * Plays new game, for a given username
     * (creates a game, plays next game turn until game has ended, notify user when needed).
     *
     * @param userName username
     * @throws IOException if score history is broken
     */
    private void playNewGame(String userName) throws IOException {
        Game game = new Game();
        this.mmi.notifyGameIsStarted();
        while (!game.isEnded()) {
            this.mmi.notifyNewTurn(game.getRemainingTurns(), game.getPoints());
            this.mmi.notifyWaitForRoll();
            this.mmi.notifyRollResult(game.playNextTurn());
        }
        this.mmi.notifyGameHasEnded(game.getPoints());
        this.updateScoreHistory(new Score(userName, game.getPoints()));
    }

    /**
     * Updates (adds to) score history with a new score.
     *
     * @param score score
     * @throws IOException if score history could not be updated
     */
    private void updateScoreHistory(Score score) throws IOException {
        this.scoreHistory.addScore(score);
    }
}
