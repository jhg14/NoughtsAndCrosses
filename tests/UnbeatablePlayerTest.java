import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

/**
 * Created by JHGWhite on 16/01/2017.
 */
public class UnbeatablePlayerTest {

    Cell[][] testBoard1 = {
            { new Cell(Symbol.O), new Cell(Symbol.O),  new Cell(Symbol.O)},
            { new Cell(Symbol.X), new Cell(Symbol.X), new Cell()},
            { new Cell(Symbol.X), new Cell(), new Cell()}
    };
    State testState1 = new State(testBoard1, 0, 2);

    Cell[][] testBoard2 = {
            { new Cell(Symbol.O), new Cell(Symbol.O),  new Cell(Symbol.X)},
            { new Cell(Symbol.X), new Cell(Symbol.X), new Cell()},
            { new Cell(Symbol.O), new Cell(), new Cell()}
    };
    State testState2 = new State(testBoard2, 0, 2);

    @Test
    public void testPlay() throws Exception {

        


    }

    @Test
    public void testMinimaxInstantLoss() throws Exception {

        UnbeatablePlayer player = new UnbeatablePlayer(Symbol.X);

        assertThat(player.minimax(testState1, Symbol.X), is(-1));
    }


    @Test
    public void testMinimaxChoiceWin() throws Exception {

        UnbeatablePlayer player = new UnbeatablePlayer(Symbol.X);

        assertThat(player.minimax(testState2, Symbol.X), is(1));
    }

}