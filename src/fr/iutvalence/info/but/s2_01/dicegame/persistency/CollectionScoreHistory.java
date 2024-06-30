package fr.iutvalence.info.but.s2_01.dicegame.persistency;

import fr.iutvalence.info.but.s2_01.dicegame.model.Score;
import fr.iutvalence.info.but.s2_01.dicegame.model.ScoreHistoryService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Collection-based implementation of score history service.
 */
public class CollectionScoreHistory implements ScoreHistoryService {

    /**
     * Scores.
     */
    List<Score> scores;

    /**
     * Creates a new collection score history instance, with no initial scores;
     */
    public CollectionScoreHistory(){
        this.scores = new ArrayList<Score>();
    }

    /**
     * Returns scores.
     *
     * @return scores
     */
    public List<Score> getScores() {
        List<Score> result = new ArrayList<Score>();
        result.addAll(this.scores);
        return result;
    }

    /**
     * Adds a score.
     *
     * @param score score
     */
    public void addScore(Score score) {
        this.scores.add(score);
    }
}
