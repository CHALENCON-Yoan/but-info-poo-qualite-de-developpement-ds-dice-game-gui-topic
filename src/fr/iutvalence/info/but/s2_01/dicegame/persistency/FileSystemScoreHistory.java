package fr.iutvalence.info.but.s2_01.dicegame.persistency;

import fr.iutvalence.info.but.s2_01.dicegame.model.InvalidScoreFormatException;
import fr.iutvalence.info.but.s2_01.dicegame.model.Score;
import fr.iutvalence.info.but.s2_01.dicegame.model.ScoreHistoryService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * File system implementation of score history service.
 * Scores are stored in a single file, one score per line (username points).
 */
public class FileSystemScoreHistory implements ScoreHistoryService {

    /**
     * Path to the file where scores are stored.
     */
    private final File filePath;

    /**
     * Cache of loaded/saved scores.
     */
    private List<Score> scores;

    /**
     * Creates a new file system score history service instance, with given filepath (scores are loaded).
     *
     * @param filePath file path
     * @throws IOException if file path lead to no file or scores could not be retrieved properly.
     */
    public FileSystemScoreHistory(String filePath) throws IOException {
        this.filePath = new File(filePath);
        this.loadScores();
    }

    @Override
    public List<Score> getScores() {
        List<Score> result = new ArrayList<Score>();
        result.addAll(this.scores);
        return result;
    }

    @Override
    public void addScore(Score score) throws IOException {
        this.scores.add(score);
        this.saveScores();
    }

    /**
     * Loads scores (from file to cache)
     *
     * @throws IOException if scores could not be retrieved properly (file not found, invalid format, ...)
     */
    private void loadScores() throws IOException {
        this.scores = new ArrayList<Score>();

        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            try {
                this.scores.add(Score.parseScore(line));
            } catch (InvalidScoreFormatException e) {
                throw new IOException();
            }
        }
    }

    /**
     * Saves scores (from cache to file).
     *
     * @throws IOException if scores could not be saved properly
     */
    public void saveScores() throws IOException {
        FileWriter writer = new FileWriter(this.filePath);

        for (Score score : this.scores)
            writer.write(score + "\n");
        writer.close();
    }
}
