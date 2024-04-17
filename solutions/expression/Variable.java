package expression;

import java.util.Objects;

public class Variable extends Expre{
    private final String var;
    public Variable(String c){
        super(c);
        this.var = c;
    }
    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z){
        return switch (var) {
            case "x" -> x;
            case "y" -> y;
            default -> z;
        };
    }

    @Override
    public String toString() {
        return var;
    }
}
