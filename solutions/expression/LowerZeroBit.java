package expression;

import java.util.Objects;

public class LowerZeroBit extends UnaryActions{
    private Unification expression;
    public LowerZeroBit(Unification expression){
        this.expression = expression;
    }

    @Override
    public int evaluate(int x) {
        int r = 0;
        int t = expression.evaluate(x);
        if (t == 0){
            return 32;
        }
        String resulrt = Integer.toBinaryString(t);
        for (int i = resulrt.length() - 1; i >= 0; i--){
            if (resulrt.charAt(i) == '1'){
                break;
            }
            r++;
        }
        return r;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int r = 0;
        int t = expression.evaluate(x, y, z);
        if (t == 0){
            return 32;
        }
        String resulrt = Integer.toBinaryString(t);
        for (int i = resulrt.length() - 1; i >= 0; i--){
            if (resulrt.charAt(i) == '1'){
                break;
            }
            r++;
        }
        return r;
    }

    @Override
    public String toString(){return "t0(" + expression.toString() + ")";}

    @Override
    public int Binary_operation(int g, int h) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LowerZeroBit that = (LowerZeroBit) o;
        return Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}
