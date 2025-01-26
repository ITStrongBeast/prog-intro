package expression;

import java.util.Objects;

public abstract class BinaryOperation implements MyExpression {
    private final MyExpression leftExpression;
    private final MyExpression rightExpression;
    private final String symbol = getSymbolOperation();

    protected BinaryOperation(MyExpression leftExpression, MyExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int evaluate(int x) {
        return calculation(leftExpression.evaluate(x), rightExpression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculation(leftExpression.evaluate(x, y, z), rightExpression.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + leftExpression.toString() + " " + symbol + " " + rightExpression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        return (checkLeft() ? "(" : "") + leftExpression.toMiniString() + (checkLeft() ? ")" : "") +
                " " + symbol + " " +
                (checkRight() ? "(" : "") + rightExpression.toMiniString() + (checkRight() ? ")" : "");
    }

    private boolean checkLeft() {
        return (Objects.equals(symbol, "*") || Objects.equals(symbol, "/"))
                && (leftExpression instanceof Add || leftExpression instanceof Subtract);
    }

    private boolean checkRight() {
        return ((Objects.equals(symbol, "*") || Objects.equals(symbol, "/") || Objects.equals(symbol, "-"))
                && (rightExpression instanceof Add || rightExpression instanceof Subtract))
                || ((Objects.equals(symbol, "*") || Objects.equals(symbol, "/"))
                && rightExpression instanceof Divide)
                || (Objects.equals(symbol, "/") && rightExpression instanceof Multiply);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BinaryOperation that)) return false;
        return Objects.equals(symbol, that.symbol)
                && Objects.equals(leftExpression, that.leftExpression)
                && Objects.equals(rightExpression, that.rightExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftExpression, rightExpression, symbol);
    }

    protected abstract String getSymbolOperation();

    protected abstract int calculation(int leftEval, int rightEval);
}
