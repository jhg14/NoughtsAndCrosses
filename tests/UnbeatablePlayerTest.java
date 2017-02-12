import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

/**
 * Created by JHGWhite on 16/01/2017.
 */

public class UnbeatablePlayerTest {


    Cell[][] testBoard1 = {
            { new Cell(Symbol.O), new Cell(Symbol.O),  new Cell()},
            { new Cell(Symbol.X), new Cell(Symbol.X), new Cell(Symbol.X)},
            { new Cell(Symbol.O), new Cell(), new Cell()}
    };
    State testState1 = new State(testBoard1);

    Cell[][] testBoard2 = {
            { new Cell(Symbol.O), new Cell(Symbol.O),  new Cell(Symbol.X)},
            { new Cell(Symbol.X), new Cell(Symbol.X), new Cell()},
            { new Cell(Symbol.O), new Cell(), new Cell()}
    };
    State testState2 = new State(testBoard2);

    Cell[][] testBoard3 = {
            { new Cell(Symbol.O), new Cell(Symbol.O),  new Cell()},
            { new Cell(Symbol.X), new Cell(Symbol.X), new Cell()},
            { new Cell(), new Cell(), new Cell()}
    };
    State testState3 = new State(testBoard3);
    Cell[][] endBoard3 = {
            { new Cell(Symbol.O), new Cell(Symbol.O),  new Cell()},
            { new Cell(Symbol.X), new Cell(Symbol.X), new Cell(Symbol.X)},
            { new Cell(), new Cell(), new Cell()}
    };
    State endState3 = new State(endBoard3);

    Cell[][] testBoard4 = {
            { new Cell(Symbol.O), new Cell(Symbol.O),  new Cell()},
            { new Cell(), new Cell(Symbol.X), new Cell()},
            { new Cell(Symbol.X), new Cell(), new Cell()}
    };
    State testState4 = new State(testBoard4);

    // This test seemingly fails for no reason - the output for 'is' and 'was' are identical
    @Test
    public void testPlayInstantWin() throws Exception {

        UnbeatablePlayer player = new UnbeatablePlayer(Symbol.X);

        assertThat(player.play(testState3), is(endState3));

    }

    @Test
    public void testMinimaxInstantWin() throws Exception {

        UnbeatablePlayer player = new UnbeatablePlayer(Symbol.X);

        assertThat(player.minimax(testState1, Symbol.O), is(1));
    }


    @Test
    public void testMinimaxChoiceWin() throws Exception {

        UnbeatablePlayer player = new UnbeatablePlayer(Symbol.X);

        assertThat(player.minimax(testState2, Symbol.X), is(1));
    }

    @Test
    public void testMinimaxLtoRDiagonal() throws Exception {

        UnbeatablePlayer player = new UnbeatablePlayer(Symbol.O);

        assertThat(player.minimax(testState4, Symbol.X), is(-1));

    }

}