import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JHGWhite on 11/01/2017.
 */

public class State {

    public final Cell[][] board;

    // Initialise a fresh state in which the board is empty
    public State() {
        board = new Cell[NoughtsAndCrosses.DIMENSION][NoughtsAndCrosses.DIMENSION];

        for (int i = 0; i < NoughtsAndCrosses.DIMENSION; i++) {
            for (int j = 0; j < NoughtsAndCrosses.DIMENSION; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    // Create a state from a pre-made board
    // Must specify what the last moves were
    // For testing purposes
    public State(Cell[][] board, Integer lastX, Integer lastY) {
        this.board = new Cell[NoughtsAndCrosses.DIMENSION][NoughtsAndCrosses.DIMENSION];

        for (int i = 0; i < NoughtsAndCrosses.DIMENSION; i++) {
            for (int j = 0; j < NoughtsAndCrosses.DIMENSION; j++) {
                this.board[i][j] = board[i][j].copy();
            }
        }
    }

    // Initialise a state from a previous state with one alteration
    public State(State prev, Symbol turn, int i, int j) {
        //Cell[][] temp = prev.board.clone();

        Cell[][] temp = new Cell[NoughtsAndCrosses.DIMENSION][NoughtsAndCrosses.DIMENSION];

        for (int k = 0; k < NoughtsAndCrosses.DIMENSION; k++) {
            for (int l = 0; l < NoughtsAndCrosses.DIMENSION; l++) {
                temp[k][l] = prev.board[k][l].copy();
            }
        }

        temp[i][j].setContents(turn);
        board = temp;
    }

    public void makeMove(Symbol symbol, int i, int j) {
        board[i][j].setContents(symbol);
    }

    public void revokeMove(int i, int j) {
        board[i][j].resetContents();
    }

    public int emptyTileCount() {
        int emptyTiles = 0;
        for (Cell[] column : board) {
            for (Cell tile: column) {
                if (tile.getContents() == Symbol.EMPTY) {
                    emptyTiles ++;
                }
            }
        }
        return emptyTiles;
    }

    public List<Coordinate> getEmptyTiles() {
        List<Coordinate> emptyTiles = new ArrayList<>();

        for (int i = 0; i < NoughtsAndCrosses.DIMENSION; i++) {
            for (int j = 0; j < NoughtsAndCrosses.DIMENSION; j++) {
                if (board[i][j].getContents() == Symbol.EMPTY) {
                    emptyTiles.add(new Coordinate(i, j));
                }
            }
        }
        return emptyTiles;
    }

    // Private method to determine whether or not the board is full
    public boolean boardFilled() {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell.getContents() == Symbol.EMPTY)
                    return false;
            }
        }
        return true;
    }

    public boolean checkForWinner(Symbol symbol) {
        for (int x = 0; x < NoughtsAndCrosses.DIMENSION; x++) {

            for (int y = 0; y < NoughtsAndCrosses.DIMENSION; y++) {

                if (board[x][y].getContents() == symbol) {

                    // Column win
                    int column = y;
                    int rowCounter = 0;
                    for (int i = 0; i < NoughtsAndCrosses.DIMENSION; i++) {
                        if (board[i][column].getContents() == symbol) {
                            rowCounter++;
                        }
                    }

                    if (rowCounter == NoughtsAndCrosses.DIMENSION)
                        return true;

                    // Row win
                    int row = x;
                    int columnCounter = 0;
                    for (int j = 0; j < NoughtsAndCrosses.DIMENSION; j++) {
                        if (board[row][j].getContents() == symbol) {
                            columnCounter++;
                        }
                    }

                    if (columnCounter == NoughtsAndCrosses.DIMENSION)
                        return true;

                    // L - R Diagonal win
                    if (x == y) {
                        int diagCounter = 0;

                        for (int ij = 0; ij < NoughtsAndCrosses.DIMENSION; ij++) {
                            if (board[ij][ij].getContents() == symbol)
                                diagCounter++;
                        }

                        if (diagCounter == NoughtsAndCrosses.DIMENSION)
                            return true;
                    }

                    // R - L Diagonal win
                    if (x + y == NoughtsAndCrosses.DIMENSION - 1) {
                        int diagCounter = 0;

                        for (int i = 0; i < NoughtsAndCrosses.DIMENSION; i++) {
                            if (board[i][NoughtsAndCrosses.DIMENSION - 1 - i].getContents() == symbol) {
                                diagCounter++;
                            }
                        }

                        if (diagCounter == NoughtsAndCrosses.DIMENSION)
                            return true;
                    }

                }
            }

        }

        return false;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (Cell[] column : board) {
            for (Cell cell: column) {
                Symbol contents = cell.getContents();
                builder.append(contents == Symbol.EMPTY ? "_" : contents);
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;
        
        return Arrays.deepEquals(board, state.board);

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }


}
