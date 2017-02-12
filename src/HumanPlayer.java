import java.util.List;
import java.util.Scanner;

/**
 * Created by JHGWhite on 11/01/2017.
 */
public class HumanPlayer extends Player {

    private Scanner input;

    public HumanPlayer(Symbol symbol, Scanner input) {
        this.symbol = symbol;
        this.input = input;
    }


    @Override
    public State play(State state) {

        boolean valid = false;

        while (!valid) {

            Coordinate coord = parseInput(input.nextInt());

            List<Coordinate> emptyTiles = state.getEmptyTiles();

            for (Coordinate emptyTile : emptyTiles) {
                if (emptyTile.equals(coord)) {
                    state.makeMove(symbol, coord.i, coord.j);
                    return state;
                }
            }
            //System.out.println("Please enter a valid location");
        }
        return null;
    }

    private Coordinate parseInput(int tile) {

        return new Coordinate((tile - 1) / NoughtsAndCrosses.DIMENSION , (tile - 1) % NoughtsAndCrosses.DIMENSION);

    }

}
