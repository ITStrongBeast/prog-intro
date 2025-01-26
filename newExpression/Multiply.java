package expression;

public class Multiply extends BinaryOperation {
    public Multiply(MyExpression leftExpression, MyExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    protected String getSymbolOperation() {
        return "*";
    }

    @Override
    protected int calculation(int leftEval, int rightEval) {
        return leftEval * rightEval;
    }
}
