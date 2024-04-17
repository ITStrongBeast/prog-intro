package game;

import java.util.Map;

import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private int mat1 = 0;
    private int mat2 = 0;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(Board board) { // :NOTE: copy-paste
        while (true) {
            final int result1 = move(board, player1, 1);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, player2, 2);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    public int plays(int m, int n, int k) {
        Board board = new TicTacToeBoard(m, n, k);
        Random random = new Random();
        int motion = random.nextInt(2) ;
        while (true) {
            if (motion == 0) {
                final int result1 = move(board, player1, 1);
                if (result1 != -1){
                    return result1;
                }
                final int result2 = move(board, player2, 2);
                if (result2 != -1) {
                    return result2;
                }
            } else {
                final int result2 = move(board, player2, 2);
                if (result2 != -1) {
                    return result2;
                }
                final int result1 = move(board, player1, 1);
                if (result1 != -1){
                    return result1;
                }
            }
        }
    }

    public int match(int m, int n, int k, int fin) {
        int r = plays(m, n, k);
        if (r == 1){
            mat1++;
        }
        if (r == 2){
            mat2++;
        }
        if (mat1 == fin){
            return 3;
        }
        if (mat2 == fin){
            return 4;
        }
        return r;
    }

    public int turn(int m, int n, int k, Map<String, String> people){ // :NOTE: Map<Integer, String>
        int result = 0;
        int counter = -1;
        int last1 = 0;
        int remains = 0;
        int start = people.size();
        String person1 = "";
        String person2 = "";
        while (people.size() != 1) {
            final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
            for (int i = counter + 1; i < start; i++) {
                if (!(people.get(Integer.toString(i)) == null)) {
                    person1 = people.get(Integer.toString(i));
                    last1 = i;
                    counter = i;
                    break;
                }
            }
            for (int j = counter + 1; j < start; j++) {
                if (!(people.get(Integer.toString(j)) == null)) {
                    person2 = people.get(Integer.toString(j));
                    counter = j;
                    break;
                }
            }
            int resulte = game.match(m, n, k, 1);
            if (resulte == -2) {
                return -2;
            }
            if (resulte == 3) { // :NOTE: result to enum
                System.out.println("Won the match: " + person1);
                System.out.println("Lost the match: " + person2);
                people.remove(Integer.toString(counter));
            } else if (resulte == 4) {
                System.out.println("Won the match: " + person2);
                System.out.println("Lost the match: " + person1);
                people.remove(Integer.toString(last1));
            }
            for (int l = counter + 1; l < start; l++) {
                if (!(people.get(Integer.toString(l)) == null)) {
                    remains++;
                }
            }
            if (remains < 2) {
                counter = -1;
            }
            remains = 0;
        }
        for (int j = 0; j < start; j++) {
            if (!(people.get(Integer.toString(j)) == null)) {
                result = j;
                break;
            }
        }
        return result;
    }

    private int move(final Board board, final Player player, final int no) {
        try {
            final Move move = player.move(board.getPosition(), board.getCell());
            if (move == null) return -2;
            final Result result = board.makeMove(move);
            log("Player " + no + " move: " + move);
            log("Position:\n" + board); // :NOTE: System.lineSeparator()
            if (result == Result.WIN) {
                log("Player " + no + " won");
                return no;
            } else if (result == Result.LOSE) {
                log("Player " + no + " lose");
                return 3 - no;
            } else if (result == Result.DRAW) {
                log("Draw");
                return 0;
            } else {
                return -1;
            }
        } catch (Exception e){
            if (player instanceof HumanPlayer){
                return -1;
            } else {
                System.out.println("your bot made a mistake");
                log("Player " + no + " lose");
                return 3 - no;
            }
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
