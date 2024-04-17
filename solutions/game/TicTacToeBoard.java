package game;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TicTacToeBoard implements Board, Position{
    private final int m;
    private final int n;
    private final int k;
    private int i;
    private int j;
    private int empty = 0;
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;

    public TicTacToeBoard(int m, int n, int k) {
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.m = m;
        this.n = n;
        this.k = k;
    }
    // (Board) urehergeuherg.getPosition()


    @Override
    public Position getPosition() {
        return this;
    }


    @Override
    public Cell getCell() {
        return turn;
    }

    private boolean vektor(int vektor_m, int vektor_n){
        boolean result = false;
        int count = 0;
        int lenght = 0;
        while (0 <= i + vektor_m * lenght && i + vektor_m * lenght < m && 0 <= j + vektor_n * lenght && j + vektor_n * lenght < n) {
            if (cells[i + vektor_m * lenght][j + vektor_n * lenght] == turn){
                count++;
            } else {
                break;
            }
            if (count == k + 1){
                break;
            }
            lenght++;
        }
        lenght = 0;
        while (0 <= i - vektor_m * lenght && i - vektor_m * lenght < m && 0 <= j - vektor_n * lenght && j - vektor_n * lenght < n) {
            if (cells[i - vektor_m * lenght][j - vektor_n * lenght] == turn ){
                count++;
            } else {
                break;
            }
            if (count == k + 1){
                break;
            }
            lenght++;
        }
        if (count == k + 1){
            result = true;
        }
        return result;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        this.i = move.getRow();
        this.j = move.getColumn();

        cells[i][j] = move.getValue();

        empty++;

        if (vektor(0, 1) || vektor(-1, 0) || vektor(-1, 1) || vektor(1, 1)) { // O(4*(k + 1))
            return Result.WIN;
        }
        if (empty == m*n) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }


    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }


    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");
        for (int b = 0; b < n; b++){
            sb.append(Integer.toString(b));
            sb.append(" ");
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < n; c++) {
                sb.append(" ".repeat(Integer.toString(c).length()));
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
