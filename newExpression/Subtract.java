package expression;

public class Subtract extends BinaryOperation {

    public Subtract(MyExpression leftExpression, MyExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    protected String getSymbolOperation() {
        return "-";
    }

    @Override
    protected int calculation(int leftEval, int rightEval) {
        return leftEval - rightEval;
    }
}
