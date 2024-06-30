package fr.iutvalence.info.but.s2_01.dicegame.model;

/**
 * Game score (username/points).
 */
public class Score implements Comparable<Score> {

    /**
     * Username.
     */
    private final String userName;

    /**
     * Points.
     */
    private final int points;

    /**
     * Creates a new score instance, with given username/points.
     *
     * @param userName username
     * @param points    points
     */
    public Score(String userName, int points) {
        this.userName = userName;
        this.points = points;
    }

    /**
     * Parses a score form an input string.
     *
     * @param line input string
     * @return parsed score
     * @throws InvalidScoreFormatException if score format is invalid
     */
    public static Score parseScore(String line) throws InvalidScoreFormatException {
        String[] tokens = line.split(" ");
        if (tokens.length != 2)
            throw new InvalidScoreFormatException();
        int points = 0;
        try {
            points = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            throw new InvalidScoreFormatException();
        }

        return new Score(tokens[0], points);
    }

    /**
     * Returns username.
     *
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns points.
     *
     * @return points
     */
    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return (getUserName() + " " + getPoints());
    }

    @Override
    public int compareTo(Score other) {
        return this.points - other.points;
    }
}
