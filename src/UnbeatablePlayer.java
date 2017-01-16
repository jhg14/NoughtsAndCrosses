import java.util.ArrayList;
import java.util.List;

/**
 * Created by JHGWhite on 11/01/2017.
 */
public class UnbeatablePlayer extends Player {

    // This is the symbol that will be used
    // to determine whether a -1 or 1 should
    // be returned from the Minimax function in
    // State

    public UnbeatablePlayer(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public State play(State state) {

        // Return the same state if game is over,
        // else return next move as new state

        /*
            Compile a list of possible moves, and apply minimax to each of them
            to discover their respective scores.
            For the first call, we know that we want to chose the highest scoring
            possible move.
         */

        List<State> possibleMoves = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();

        List<Coordinate> possibleSymbolLocations = state.getEmptyTiles();

        possibleSymbolLocations.forEach(coordinate ->
            possibleMoves.add(new State(state, symbol, coordinate.i, coordinate.j)));

        for (State move: possibleMoves) {
            int value = minimax(move, symbol.getOpponent());
            if (value == 1) {
                return move;
            }
            scores.add(value);
        }

        return possibleMoves.get(getMaxIndex(scores));

    }

    public int minimax(State state, Symbol toPlace) {

        /*

            If the current state is a win condition, return 1,
            If it is a lose condition, return -1
            If it is a draw condition, return 0
            If the game is not over, run minimax on all possible moves

         */

        Symbol lastPlaced = toPlace.getOpponent();

        // Column win
        int column = state.lastY;
        int rowCounter = 0;
        for (int j = 0; j < NoughtsAndCrosses.DIMENSION; j++) {
            if (state.board[j][column].getContents() == lastPlaced) {
                rowCounter ++;
            }
        }
        if (rowCounter == NoughtsAndCrosses.DIMENSION)
            return lastPlaced == symbol ? 1 : -1;

        // Row win
        int row = state.lastX;
        int columnCounter = 0;
        for (int i = 0; i < NoughtsAndCrosses.DIMENSION; i++) {
            if (state.board[row][i].getContents() == lastPlaced) {
                columnCounter ++;
            }
        }
        if (columnCounter == NoughtsAndCrosses.DIMENSION)
            return lastPlaced == symbol ? 1 : -1;

        // Diagonal win
        if (state.lastX == state.lastY) {
            int diagCounter = 0;

            for (int ij = 0; ij < NoughtsAndCrosses.DIMENSION; ij++) {
                if (state.board[ij][ij].getContents() == lastPlaced)
                    diagCounter ++;
            }

            if (diagCounter == NoughtsAndCrosses.DIMENSION)
                return lastPlaced == symbol ? 1 : -1;
        }

        // Draw
        if (state.boardFilled())
            return 0;


        // Create a list of all possible next states
        List<State> possibleMoves = new ArrayList<>();

        // Create a list of possible tiles for a symbol to be placed in
        List<Coordinate> possibleSymbolLocations = state.getEmptyTiles();

        // Create a list of scores corresponding to each move
        List<Integer> scores = new ArrayList<>();

        // Populate the state list with new states for each available tile
        possibleSymbolLocations.forEach(coordinate ->
                possibleMoves.add(new State(state, toPlace, coordinate.i, coordinate.j)));

        // Run minimax on each possible next state
        // Depending on whose turn it is, the minimum or maximum score
        // is returned
        // Optimised to short circuit
        for (State move: possibleMoves) {
            int value = minimax(move, lastPlaced);

            if (value == 1 && toPlace == symbol) {
                return 1;
            } else if (value == -1 && toPlace == symbol.getOpponent()) {
                return -1;
            }
            scores.add(value);
        }

        // If the next symbol to be placed was the player's symbol, return the max score,
        // otherwise minimise the opponent's score
        return toPlace == symbol ? scores.get(getMaxIndex(scores)) : scores.get(getMinIndex(scores));
    }


    // Utility function: returns the index of the first maximum element in a list
    private int getMaxIndex(List<Integer> list) {
        int max = -2;
        int maxIndex = -1;

        for (int i : list) {
            if (i > max) {
                max = i;
                maxIndex = list.indexOf(i);
            }
        }
        return maxIndex;
    }

    // Utility function: returns the index of the first minimum element in a list
    private int getMinIndex(List<Integer> list) {

        int min = 2;
        int minIndex = -1;

        for (int i : list) {
            if (i < min) {
                min = i;
                minIndex = list.indexOf(i);
            }
        }
        return minIndex;
    }
}
