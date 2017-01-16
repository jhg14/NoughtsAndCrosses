import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.Scanner;

/**
 * Created by JHGWhite on 11/01/2017.
 */
public class NoughtsAndCrosses {

    public static final int DIMENSION = 3;
    public static final int NUMBER_OF_PLAYERS = 2;

    public static void main(String[] args) {

        // Test

        Cell[][] testBoard = {
                { new Cell(Symbol.O), new Cell(Symbol.O),  new Cell()},
                { new Cell(Symbol.X), new Cell(), new Cell()},
                { new Cell(Symbol.X), new Cell(), new Cell()}
        };

        State currentState = new State(testBoard, 0, 1);



        // Test









        Player[] players = new Player[NUMBER_OF_PLAYERS];

        Player human = new HumanPlayer(Symbol.X ,new Scanner(System.in));
        Player human2 = new HumanPlayer(Symbol.O, new Scanner(System.in));
        Player computer = new UnbeatablePlayer(Symbol.O);

        players[0] = human;
        players[1] = computer;

        // Initialise first state
        //State currentState = new State();

        boolean winner = false;
        while (!winner) {

            for (Player player : players) {

                System.out.println(currentState);

                State newState = player.play(currentState);

                if (newState.equals(currentState)) {
                    // The game is over
                    System.out.println("This game was a draw");
                    winner = true;
                    break;
                } else {
                    currentState = newState;
                    if (checkForWinner(player, currentState)) {
                        System.out.println(player.symbol + " is the winner.");
                        winner = true;
                        break;
                    } else if (currentState.boardFilled()) {
                        System.out.println("This game was a draw");
                        winner = true;
                        break;
                    }
                }
            }
        }
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
