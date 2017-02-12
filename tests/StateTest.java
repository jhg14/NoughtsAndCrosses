/**
 * Created by JHGWhite on 12/02/2017.
 */

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

import static org.junit.Assert.*;

public class StateTest {

    Cell[][] emptyBoard1 = {
            { new Cell(), new Cell(),  new Cell()},
            { new Cell(), new Cell(), new Cell()},
            { new Cell(), new Cell(), new Cell()}
    };
    State emptyState1 = new State(emptyBoard1, null, null);

    Cell[][] emptyBoard2 = {
            { new Cell(), new Cell(),  new Cell()},
            { new Cell(), new Cell(), new Cell()},
            { new Cell(), new Cell(), new Cell()}
    };
    State emptyState2 = new State(emptyBoard2, null, null);


    Cell[][] testBoard1 = {
            { new Cell(Symbol.O), new Cell(Symbol.O),  new Cell()},
            { new Cell(), new Cell(Symbol.X), new Cell()},
            { new Cell(Symbol.X), new Cell(), new Cell()}
    };
    State testState1 = new State(testBoard1, 0, 0);

    Cell[][] testBoard2 = {
            { new Cell(Symbol.O), new Cell(Symbol.O),  new Cell()},
            { new Cell(), new Cell(Symbol.X), new Cell()},
            { new Cell(Symbol.X), new Cell(), new Cell()}
    };
    State testState2 = new State(testBoard2, 0, 1);

    @Test
    public void stateEqualsFunctionsCorrectly() throws Exception {

        assertThat(emptyState1, is(emptyState2));
        assertThat(testState1, is(testState2));

    }

    Cell[][] winCondition1 = {
            { new Cell(Symbol.X), new Cell(),  new Cell()},
            { new Cell(), new Cell(Symbol.X), new Cell()},
            { new Cell(), new Cell(), new Cell(Symbol.X)}
    };

    Cell[][] winCondition2 = {
            { new Cell(), new Cell(),  new Cell(Symbol.X)},
            { new Cell(), new Cell(Symbol.X), new Cell()},
            { new Cell(Symbol.X), new Cell(), new Cell()}
    };

    Cell[][] winCondition3 = {
            { new Cell(), new Cell(),  new Cell()},
            { new Cell(Symbol.X), new Cell(Symbol.X), new Cell(Symbol.X)},
            { new Cell(), new Cell(), new Cell()}
    };

    Cell[][] winCondition4 = {
            { new Cell(), new Cell(Symbol.X),  new Cell()},
            { new Cell(), new Cell(Symbol.X), new Cell()},
            { new Cell(), new Cell(Symbol.X), new Cell()}
    };

    State winState1 = new State(winCondition1, 1, 1);
    State winState2 = new State(winCondition2, 1, 1);
    State winState3 = new State(winCondition3, 1, 1);
    State winState4 = new State(winCondition4, 1, 1);

    @Test
    public void checkForWinFunctionsCorrectly() throws Exception {

        assertThat(winState1.checkForWinner(Symbol.X), is(true));
        assertThat(winState2.checkForWinner(Symbol.X), is(true));
        assertThat(winState3.checkForWinner(Symbol.X), is(true));
        assertThat(winState4.checkForWinner(Symbol.X), is(true));

    }

}
