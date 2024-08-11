package game;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner inp = new Scanner(System.in)){
            Map<String, String> people = new HashMap<>();
            int m, n, k, number_of_players, tourn;
            while (true) {
                System.out.println("If you want to play a tournament, press 1 and enter the number of players, otherwise enter 0: ");
                try (Scanner turn = new Scanner(inp.nextLine())){
                    if (!turn.hasNextInt()) {
                        throw new InputMismatchException();
                    }
                    tourn = turn.nextInt();
                    if (tourn > 1) {
                        throw new InputMismatchException();
                    }
                    if (tourn == 1) {
                        if (!turn.hasNextInt()) {
                            throw new InputMismatchException();
                        }
                        number_of_players = turn.nextInt();
                        if (turn.hasNextInt()) {
                            throw new InputMismatchException();
                        }
                            for (int i = 0; i < number_of_players; i++) {
                            people.put(Integer.toString(i), "Player" + i);
                        }
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Enter either 0 or 1 as the first number");
                }
            }
            while (true) {
                m = 0;
                n = 0;
                k = 0;
                System.out.println("Enter the dimensions of the field: "); // :NOTE: input example
                try (Scanner in = new Scanner(inp.nextLine())){
                    m = in.nextInt();
                    n = in.nextInt();
                    k = in.nextInt();
                    if (k <= 0 || m < 0 || n < 0 || in.hasNextInt() || k > m && k > n) {
                        throw new InputMismatchException();
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("enter 3 numbers");
                }
            }
            final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
            int result;
            if (tourn == 1) {
                result = game.turn(m, n, k, people);
                if (result == -2) throw new NoSuchElementException();
                System.out.println("The player won the tournament: " + result);
            } else {
                do {
                    result = game.play(new TicTacToeBoard(m, n, k));
                    if (result == -2) throw new NoSuchElementException();
                    System.out.println("Game result: " + result);
                } while (result != 0);
            }
        } catch (NoSuchElementException e){
            System.out.println("Game stop");
        }
    }
}
