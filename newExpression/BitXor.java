package expression;

public class BitXor extends BinaryOperation {
    public BitXor(MyExpression leftExpression, MyExpression rightExpression) {
        super(leftExpression, rightExpression);
    }

    @Override
    protected String getSymbolOperation() {
        return "^";
    }

    @Override
    protected int calculation(int leftEval, int rightEval) {
        return leftEval ^ rightEval;
    }
}
