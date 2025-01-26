package expression;

public class Const extends Items {
    private final int value;

    public Const(int value) {
        super(String.valueOf(value));
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }
}
