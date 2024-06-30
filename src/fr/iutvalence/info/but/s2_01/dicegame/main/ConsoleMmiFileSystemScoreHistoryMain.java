package fr.iutvalence.info.but.s2_01.dicegame.main;

import fr.iutvalence.info.but.s2_01.dicegame.mmi.ConsoleMmi;
import fr.iutvalence.info.but.s2_01.dicegame.model.Mmi;
import fr.iutvalence.info.but.s2_01.dicegame.model.DiceGameManager;
import fr.iutvalence.info.but.s2_01.dicegame.persistency.FileSystemScoreHistory;
import fr.iutvalence.info.but.s2_01.dicegame.model.ScoreHistoryService;

import java.io.IOException;

/**
 * Dice game application launcher, console mode, file system score history.
 */
public class ConsoleMmiFileSystemScoreHistoryMain {

    /**
     * Application's main
     * (creates a new console Mmi instance, a new "filesystem score history" instance with given path,
     * a dice game manager instance linked to previous ones, and finally starts dice game manager).
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        Mmi mmi = new ConsoleMmi();
        ScoreHistoryService scoreHistory = null;
        try {
            scoreHistory = new FileSystemScoreHistory("./scores.dcg");
        } catch (IOException e) {
           mmi.notifyScoreHistoryIsBroken();
           System.exit(1);
        }
        new DiceGameManager(scoreHistory, mmi).start();
    }
}
