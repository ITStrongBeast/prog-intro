package expression;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        Expression exp = new Add(
                new Subtract(
                    new Multiply(
                            new Variable("x"),
                            new Variable("x")
                    ),
                    new Multiply(
                            new Const(2),
                            new Variable("x")
                    )
                ),
                new Const(1)
        );
        System.out.println(exp);
        System.out.println(exp.evaluate(Integer.parseInt("5")));
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse(" ").toString());
    }
}

/*

 ((( * ) + ( - ))  /  3)

 ((2 * (3 + x)) - 3)

new Subtract(
    new Multiply(
        new Const(2),
        new Variable("x")
    ),
    new Const(3)
)

* */

// E = ((2 * (3 + x)) - 3) new Sub(new Mult(new Const(2), new Add(new Const(3), new Var(x))), new Const(3));
/*
T = (2 * (3 + x)) new Mult(new Const(2), new Add(new Const(3), new Var(x)))
    I = 2 ! new Const(2)
    I = (3 + x) new Add(new Const(3), new Var(x))
        E = (3 + x)  new Add(new Const(3), new Var(x))
            T = 3 new Const(3)
            T = x new Var(x)

T = 3   ! new Const(3)
 */





// E = ((2 * x) - 3) new Sabtract(new Mult(new Const(2), new Var(x)), new Const(3));
/*
T = (2 * x) new Mult(new Const(2), new Var(x));
    I = 2 ! new Const
    I = x ! new Var

T = 3   ! new Const(3)
 */




/*
*  E -> (T) + (T)
*  E -> (T) - (T)
*  E -> T
*
*  T -> (I) * (I)
*  T -> (I) / (I)
*  T -> I
*
*  I -> (E)
*  I -> -(E)
*  I -> C
*  I -> V
*
* */