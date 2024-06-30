package fr.iutvalence.info.but.s2_01.dicegame.model;

import fr.iutvalence.info.but.s2_01.dicegame.model.Score;

import java.io.IOException;
import java.util.List;

/**
 * General contract for score history service.
 */
public interface ScoreHistoryService {

    /**
     * Returns scores.
     *
     * @return scores
     */
    List<Score> getScores();

    /**
     * Adds a new score.
     *
     * @param score score
     * @throws IOException if score history could not be saved.
     */
    void addScore(Score score) throws IOException;
}
