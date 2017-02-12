import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JHGWhite on 11/01/2017.
 */
public class UnbeatablePlayer extends Player {

    private HashMap<State, Integer> cache;

    public UnbeatablePlayer(Symbol symbol) {

        this.symbol = symbol;
        this.cache = new HashMap<>();
    }

    /*
        Compile a list of possible moves, and apply minimax to each of them
        to discover their respective scores.
        For the first call, we know that we want to chose the highest scoring
        possible move.
        Requires first step to be done here so that we have access to the state
        with the highest score as opposed to just its score
    */
    @Override
    public State play(State state) {

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

    /*
        If the current state is a win condition, return 1,
        If it is a lose condition, return -1
        If it is a draw condition, return 0
        If the game is not over, run minimax on all possible moves
    */
    public int minimax(State state, Symbol toPlace) {

        Symbol lastPlaced = toPlace.getOpponent();

        // If the last move caused a win condition,
        // return 1 if it was this player's,
        // else return -1 if it was the opponent
        if (state.checkForWinner(lastPlaced))
            return lastPlaced == symbol ? 1 : -1;

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
        for (Coordinate coordinate : possibleSymbolLocations) {

            //possibleMoves.add(new State(state, toPlace, coordinate.i, coordinate.j));
            int value;
            int i = coordinate.i;
            int j = coordinate.j;
            Coordinate prevLast = state.makeMove(toPlace, i, j);

            if (cache.containsKey(state)) {
                value = cache.get(state);
            } else {
                value = minimax(state, lastPlaced);
                cache.put(new State(state, lastPlaced, i, j), value);
            }

            scores.add(value);
            state.revokeMove(i, j, prevLast);

            if (value == 1 && toPlace == symbol) {
                //System.out.println("Short Circuit max");
                return 1;
            } else if (value == -1 && toPlace == symbol.getOpponent()) {
                //System.out.println("Short Circuit min");
                return -1;
            }

        }

        // Run minimax on each possible next state
        // Depending on whose turn it is, the minimum or maximum score
        // is returned
        // Optimised to short circuit
//        for (State move: possibleMoves) {
//            int value;
//            if (!cache.containsKey(move)) {
//                value = minimax(move, lastPlaced);
//                cache.put(move, value);
//                System.out.println(cache.size());
//                if (value == 1 && toPlace == symbol) {
//                    //System.out.println("Short Circuit max");
//                    return 1;
//                } else if (value == -1 && toPlace == symbol.getOpponent()) {
//                    //System.out.println("Short Circuit min");
//                    return -1;
//                }
//            } else {
//                //System.out.println("Drawing from cache");
//                value = cache.get(move);
//            }
//            scores.add(value);
//        }

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
