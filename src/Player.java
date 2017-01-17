/**
 * Created by JHGWhite on 11/01/2017.
 */
public abstract class Player {

    // The players given symbol
    protected Symbol symbol;

    public abstract State play(State state);

}
