package expression;

public class Negate implements MyExpression{
    private final MyExpression expression;

    public Negate(MyExpression expression) {
        this.expression = expression;
    }

    @Override
    public int evaluate(int x) {
        return -1 * expression.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -1 * expression.evaluate(x, y, z);
    }

    @Override
    public String toString(){
        return "-(" + expression.toString() + ")";
    }
}
