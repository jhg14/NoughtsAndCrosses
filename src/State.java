/**
 * Created by JHGWhite on 11/01/2017.
 */

public class State {

    public final Cell[][] board;

    public final Integer lastX;
    public final Integer lastY;
    public final Symbol lastSymbol;

    // Initialise a fresh state in which the board is empty
    public State() {
        board = new Cell[NoughtsAndCrosses.DIMENSION][NoughtsAndCrosses.DIMENSION];
        lastX = null;
        lastY = null;
        lastSymbol = null;
    }

    // Initialise a state from a previous state with one alteration
    public State(State prev, Symbol turn, int i, int j) {
        Cell[][] temp = prev.board.clone();
        temp[i][j].setContents(turn);
        board = temp;
        lastX = i;
        lastY = j;
        lastSymbol = turn;
    }

    // Private method to determine whether or not the board is full
    private boolean gameIsOver() {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell.getContents() == Symbol.EMPTY)
                    return false;
            }
        }
        return true;
    }

    // Returns the relative score of the board, for Minimax purposes
    // If no wins then 0,
    // If enemy wins then -1,
    // If friendly wins then 1
    // TODO: Improve, remove hardcoding
    // TODO: Return -1 or 1 based on global variable Turn which determines who's turn it is
    public int getScore() {

        // Column win
        int column = lastY;
        int rowCounter = 0;
        for (int j = 0; j < NoughtsAndCrosses.DIMENSION; j++) {
            if (board[column][j].getContents() == lastSymbol) {
                rowCounter ++;
            }
        }

        if (rowCounter == NoughtsAndCrosses.DIMENSION)
            return 1;

        // Row win
        int row = lastX;
        int columnCounter = 0;
        for (int i = 0; i < NoughtsAndCrosses.DIMENSION; i++) {
            if (board[i][row].getContents() == lastSymbol) {
                columnCounter ++;
            }
        }

        if (columnCounter == NoughtsAndCrosses.DIMENSION)
            return 1;

        // Diagonal win
        if (lastX == lastY) {
            int diagCounter = 0;

            for (int ij = 0; ij < NoughtsAndCrosses.DIMENSION; ij++) {
                if (board[ij][ij].getContents() == lastSymbol)
                    diagCounter ++;
            }

            if (diagCounter == NoughtsAndCrosses.DIMENSION)
                return 1;
        }

        return 0;

    }



}
