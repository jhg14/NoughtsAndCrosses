import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * Created by JHGWhite on 11/01/2017.
 */
public class UnbeatablePlayer extends Player {

    private HashMap<Integer, Integer> cache;

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

        List<Coordinate> possibleMoves = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();

        List<Coordinate> possibleSymbolLocations = state.getEmptyTiles();

        for (Coordinate c : possibleSymbolLocations) {
            state.makeMove(symbol, c.i, c.j);
            int value = minimax(state, symbol.getOpponent());
            state.revokeMove(c.i, c.j);
            scores.add(value);
        }

        Coordinate best = possibleSymbolLocations.get(getMaxIndex(scores));
        state.makeMove(symbol, best.i, best.j);
        return state;

    }


    /*
        If the current state is a win condition, return 1,
        If it is a lose condition, return -1
        If it is a draw condition, return 0
        If the game is not over, run minimax on all possible moves
    */
    public int minimax(State state, Symbol turn) {
        if (cache.containsKey(state.hashCode())) return cache.get(state.hashCode());
        if (state.checkForWinner(symbol)) return state.emptyTileCount();
        if (state.checkForWinner(symbol.getOpponent())) return -state.emptyTileCount();
        if (state.emptyTileCount() == 0) return 0;
        List<Integer> scores = new ArrayList<>();
        for (Coordinate c : state.getEmptyTiles()) {
            state.makeMove(turn, c.i, c.j);
            scores.add(minimax(state, turn.getOpponent()));
            state.revokeMove(c.i, c.j);
        }

        int score = turn == symbol ? Collections.max(scores) : Collections.min(scores);
        cache.put(state.hashCode(), score);
        return score;
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
