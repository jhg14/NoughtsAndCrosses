import java.util.Scanner;

/**
 * Created by JHGWhite on 11/01/2017.
 */
public class NoughtsAndCrosses {

    public static final int DIMENSION = 3;

    public static void main(String[] args) {

//        Player computer = new UnbeatablePlayer();
        Player human = new HumanPlayer(Symbol.X ,new Scanner(System.in));
        Player human2 = new HumanPlayer(Symbol.O, new Scanner(System.in));


        // Initialise first state
        State currentState = new State();

        System.out.println(currentState);


    }

}
