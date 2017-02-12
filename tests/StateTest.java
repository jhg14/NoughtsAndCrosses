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

}
