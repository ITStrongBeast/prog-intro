package expression;

public class Subtract extends Actions{
    public Subtract(Unification left_expression, Unification right_expression){
        super(left_expression, "-", right_expression);
    }

    public int Binary_operation(int left_result, int right_result){
        return  left_result - right_result;
    }
}
