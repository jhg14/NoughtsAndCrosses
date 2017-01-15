import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.util.List;
import java.util.Scanner;

/**
 * Created by JHGWhite on 11/01/2017.
 */
public class HumanPlayer implements Player {

    private Symbol symbol;

    private Scanner input;

    public HumanPlayer(Symbol symbol, Scanner input) {
        this.symbol = symbol;
        this.input = input;
    }


    @Override
    public State play(State state) {

        // Return the same state if the game is over
        if (state.boardFilled())
            return state;

        boolean valid = false;

        while (!valid) {

            System.out.println("Please enter an x co-ordinate followed by a y co-ordinate");

            Coordinate coord = new Coordinate(input.nextInt(), input.nextInt());

            List<Coordinate> emptyTiles = state.getEmptyTiles();

            for (Coordinate emptyTile : emptyTiles) {
                if (emptyTile.equals(coord)) {
                    valid = true;

                    State newState = new State(state, symbol, coord.i, coord.j);

                    return newState;

                }
            }

            System.out.println("Please enter a valid location");

        }

        return null;

    }
}
