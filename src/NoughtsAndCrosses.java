import java.util.Scanner;

/**
 * Created by JHGWhite on 11/01/2017.
 */
public class NoughtsAndCrosses {

    public static final int DIMENSION = 4;
    public static final int NUMBER_OF_PLAYERS = 2;

    public static void main(String[] args) {

        Player[] players = getPlayerConfiguration();

        // Initialise first state
        State currentState = new State();

        // Main loop
        boolean winner = false;
        while (!winner) {

            // Allow each player to play()
            for (Player player : players) {

                System.out.println(currentState);

                State newState = player.play(currentState);

                currentState = newState;
                if (currentState.checkForWinner(player.symbol)) {
                    System.out.println(currentState);
                    System.out.println(player.symbol + " is the winner.");
                    winner = true;
                    break;
                } else if (currentState.boardFilled()) {
                    System.out.println(currentState);
                    System.out.println("This game was a draw");
                    winner = true;
                    break;
                }
            }
        }
    }


    // Allow the user to choose the number of human/computer players
    public static Player[] getPlayerConfiguration() {

        Player[] configuration = new Player[NUMBER_OF_PLAYERS];
        Symbol[] symbols = Symbol.values();
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many human players? (0 - " + NUMBER_OF_PLAYERS + ")");
        int humans;
        do {
            humans = scanner.nextInt();
        } while (!(humans >= 0 && humans <= NUMBER_OF_PLAYERS));

        boolean humanFirst = true;
        if (humans < NUMBER_OF_PLAYERS && humans > 0) {
            System.out.println("Would you like to go first? (y/n)");

            String first;

            while (!(first = scanner.next()).equals("y") && !first.equals("n")) {
                System.out.println("Please enter a valid character");
            }

            humanFirst = first.equals("y");
        }

        if (humanFirst) {
            for (int i = 0; i < humans; i++) {
                configuration[i] = new HumanPlayer(symbols[i], new Scanner(System.in));
            }

            for (int i = humans; i < NUMBER_OF_PLAYERS; i++) {
                configuration[i] = new UnbeatablePlayer(symbols[i]);
            }

        } else {
            for (int i = 0; i < NUMBER_OF_PLAYERS - humans; i++) {
                configuration[i] = new UnbeatablePlayer(symbols[i]);
            }

            for (int i = NUMBER_OF_PLAYERS - humans; i < NUMBER_OF_PLAYERS; i++) {
                configuration[i] = new HumanPlayer(symbols[i], new Scanner(System.in));
            }
        }

        return configuration;
    }
}
