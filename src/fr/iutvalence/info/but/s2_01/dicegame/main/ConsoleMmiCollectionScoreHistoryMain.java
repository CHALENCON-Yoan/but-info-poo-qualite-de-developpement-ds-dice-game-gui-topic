package fr.iutvalence.info.but.s2_01.dicegame.main;

import fr.iutvalence.info.but.s2_01.dicegame.mmi.ConsoleMmi;
import fr.iutvalence.info.but.s2_01.dicegame.model.DiceGameManager;
import fr.iutvalence.info.but.s2_01.dicegame.persistency.CollectionScoreHistory;

/**
 * Dice game application launcher, console mode, collection-based score history.
 */
public class ConsoleMmiCollectionScoreHistoryMain {

    /**
     * Application's main (creates a new "collection score history" instance, a new console Mmi instance,
     * a dice game manager instance linked to previous ones, and finally starts dice game manager).
     *
     *
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        new DiceGameManager(new CollectionScoreHistory(), new ConsoleMmi()).start();
    }
}
