package fr.iutvalence.info.but.s2_01.dicegame.mmi;

import fr.iutvalence.info.but.s2_01.dicegame.model.*;

import java.util.List;
import java.util.Scanner;

/**
 * Console-based Man-Machine-Interface implementation.
 */
public class ConsoleMmi implements Mmi {

    /**
     * Scanner used to read input commands.
     */
    private final Scanner in;

    /**
     * Creates a new console MMI instance, using <i>System.in</i> as input stream.
     */
    public ConsoleMmi() {
        this.in = new Scanner(System.in);
    }

    @Override
    public void notifyGameIsStarted() {
        System.out.println("Game has started!");
    }

    @Override
    public void notifyNewTurn(int remainingTurns, int score) {
        System.out.println(remainingTurns + " turns remaining");
        System.out.println(score + " points");
    }

    @Override
    public void notifyWaitForRoll() {
        System.out.println("Press <Return> to roll dices");
        this.in.nextLine();
    }

    @Override
    public void notifyRollResult(GameTurnInfo gameTurnInfo) {
        System.out.println("Roll results:");
        try {
            System.out.println("Dice 1: " + gameTurnInfo.getDiceFaceValue(0));
            System.out.println("Dice 2: " + gameTurnInfo.getDiceFaceValue(1));
        } catch (InvalidDiceNumberException e) {
            // can not occur here
        }
        System.out.println("Score: " + gameTurnInfo.getPoints());
    }

    @Override
    public void notifyGameHasEnded(int score) {
        System.out.println("Game has ended!");
        System.out.println("Final score: " + score);
        System.out.println("Press <Return> to go back to main menu");
        this.in.nextLine();
    }

    @Override
    public Choice askForChoice() {
        return this.displayMainMenu();
    }

    @Override
    public String askForUserName() {
        String userName = "";
        while (true) {
            System.out.println("Enter username:");
            userName = this.in.nextLine();
            if ((userName.equals("") || (!userName.contains(" "))))
                return userName;
            System.out.println("Invalid username, empty string or string containing spaces is forbidden");
        }
    }

    @Override
    public void notifyScoreHistoryIsBroken() {
        System.err.println("Score history is broken, unable to read or write");
    }

    /**
     * Displays main menu, returning user choice.
     * @return user choice
     */
    private Choice displayMainMenu() {
        System.out.println("---------- ");
        System.out.println("Main menu: ");
        System.out.println("---------- ");
        System.out.println("Play -> P");
        System.out.println("View high scores -> H");
        System.out.println("Exit -> X");
        System.out.println("---------- ");

        switch (this.readCommandUntilValid(new String[]{"P", "H", "X"})) {
            case "P":
                System.out.println("Play new game...");
                return Choice.PLAY_NEW_GAME;
            case "H":
                System.out.println("View high scores...");
                return Choice.HIGH_SCORES;
            case "X":
            default:
                System.out.println("Exiting...");
                return Choice.EXIT;
        }
    }

    @Override
    public void displayHighScores(List<Score> scores) {
        System.out.println("------------");
        System.out.println("High scores:");
        System.out.println("------------");
        if ((scores == null) || (scores.isEmpty()))
            System.out.println("(empty)");
        for (Score score : scores)
            System.out.println(score);
        System.out.println();
        System.out.println("Press <Return> to go back to main menu");
        this.in.nextLine();
    }

    /**
     * Reads a command until valid (part of a valid command set).
     *
     * @param validCommands valid commands
     * @return read (valid) command
     */
    private String readCommandUntilValid(String[] validCommands) {

        while (true) {
            System.out.println(" command ? ");
            String command = this.in.nextLine();
            for (String validCommand : validCommands)
                if (command.equals(validCommand))
                    return command;
            System.out.println("<invalid command>");
        }
    }
}
