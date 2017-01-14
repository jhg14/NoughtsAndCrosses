import java.util.ArrayList;
import java.util.List;

/**
 * Created by JHGWhite on 11/01/2017.
 */
public class UnbeatablePlayer implements Player {

    // This is the symbol that will be used
    // to determine whether a -1 or 1 should
    // be returned from the Minimax function in
    // State
    private Symbol symbol;

    @Override
    public State play(State state) {


        // Create and populate a list of possible moves
        List<State> possibleMoves = new ArrayList<>();
        for (int i = 0; i < NoughtsAndCrosses.DIMENSION; i++) {
            for (int j = 0; j < NoughtsAndCrosses.DIMENSION; j++) {
                if (state.board[i][j].getContents() == Symbol.EMPTY) {
                    possibleMoves.add(new State(state, symbol, i, j));
                }
            }
        }

        //TODO: RUN MINMAX ON POSSIBLE MOVES HERE

        //Chose the one with the highest score
        int maxScore = -2;
        State maxState = null;
        for(State newState : possibleMoves) {
            int stateScore = newState.getScore();
            if (stateScore > maxScore){
                maxScore = stateScore;
                maxState = newState;
            }
        }

        return maxState;

    }

    private State minimax(State state, Symbol current) {

        /*

            If the current state is a win condition, return 1,
            If it is a lose condition, return -1
            If it is a draw condition, return 0
            If the game is not over, run minimax on all possible moves

         */

        // Column win
        int column = state.lastY;
        int rowCounter = 0;
        for (int j = 0; j < NoughtsAndCrosses.DIMENSION; j++) {
            if (state.board[column][j].getContents() == state.lastSymbol) {
                rowCounter ++;
            }
        }

        if (rowCounter == NoughtsAndCrosses.DIMENSION)
            return state.lastSymbol == symbol ? 1 : -1;

        // Row win
        int row = state.lastX;
        int columnCounter = 0;
        for (int i = 0; i < NoughtsAndCrosses.DIMENSION; i++) {
            if (state.board[i][row].getContents() == state.lastSymbol) {
                columnCounter ++;
            }
        }

        if (columnCounter == NoughtsAndCrosses.DIMENSION)
            return state.lastSymbol == symbol ? 1 : -1;

        // Diagonal win
        if (state.lastX == state.lastY) {
            int diagCounter = 0;

            for (int ij = 0; ij < NoughtsAndCrosses.DIMENSION; ij++) {
                if (state.board[ij][ij].getContents() == state.lastSymbol)
                    diagCounter ++;
            }

            if (diagCounter == NoughtsAndCrosses.DIMENSION)
                return state.lastSymbol == symbol ? 1 : -1;
        }

        return 0;

    }
}
