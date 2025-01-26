package expression;

public class Variable extends Items {
    private final String value;

    public Variable(String value) {
        super(value);
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (value) {
            case "x" -> x;
            case "y" -> y;
            default -> z;
        };
    }
}
