package expression;

public class BitOr extends BinaryOperation {
    public BitOr(MyExpression leftExpression, MyExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    protected String getSymbolOperation() {
        return "|";
    }

    @Override
    protected int calculation(int leftEval, int rightEval) {
        return leftEval | rightEval;
    }
}
