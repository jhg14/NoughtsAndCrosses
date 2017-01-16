import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.Scanner;

/**
 * Created by JHGWhite on 11/01/2017.
 */
public class NoughtsAndCrosses {

    public static final int DIMENSION = 5;
    public static final int NUMBER_OF_PLAYERS = 2;

    public static void main(String[] args) {

        Player[] players = getPlayerConfiguration();

        // Initialise first state
        State currentState = new State();

        boolean winner = false;
        while (!winner) {

            for (Player player : players) {

                State newState = player.play(currentState);

                currentState = newState;
                if (checkForWinner(player, currentState)) {
                    System.out.println(currentState);
                    System.out.println(player.symbol + " is the winner.");
                    winner = true;
                    break;
                } else if (currentState.boardFilled()) {
                    System.out.println(currentState);
                    System.out.println("This game was a draw");
                    winner = true;
                    break;
                }
            }
        }
    }

    public static Player[] getPlayerConfiguration() {

        Player[] configuration = new Player[NUMBER_OF_PLAYERS];
        Symbol[] symbols = Symbol.values();
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many human players? (0 - " + NUMBER_OF_PLAYERS + ")");
        int humans;
        do {
            humans = scanner.nextInt();
        } while (!(humans >= 0 && humans <= NUMBER_OF_PLAYERS));

        for (int i = 0; i < humans; i++) {
            configuration[i] = new HumanPlayer(symbols[i], new Scanner(System.in));
        }

        for (int i = humans; i < NUMBER_OF_PLAYERS; i++) {
            configuration[i] = new UnbeatablePlayer(symbols[i]);
        }

        return configuration;
    }

    public static boolean checkForWinner(Player player, State state) {

        Symbol lastPlaced = player.symbol;

        // Column win
        int column = state.lastY;
        int rowCounter = 0;
        for (int j = 0; j < NoughtsAndCrosses.DIMENSION; j++) {
            if (state.board[j][column].getContents() == lastPlaced) {
                rowCounter ++;
            }
        }

        if (rowCounter == NoughtsAndCrosses.DIMENSION)
            return true;

        // Row win
        int row = state.lastX;
        int columnCounter = 0;
        for (int i = 0; i < NoughtsAndCrosses.DIMENSION; i++) {
            if (state.board[row][i].getContents() == lastPlaced) {
                columnCounter ++;
            }
        }

        if (columnCounter == NoughtsAndCrosses.DIMENSION)
            return true;

        // Diagonal win
        if (state.lastX == state.lastY) {
            int diagCounter = 0;

            for (int ij = 0; ij < NoughtsAndCrosses.DIMENSION; ij++) {
                if (state.board[ij][ij].getContents() == lastPlaced)
                    diagCounter ++;
            }

            if (diagCounter == NoughtsAndCrosses.DIMENSION)
                return true;
        }

        return false;
    }



}
