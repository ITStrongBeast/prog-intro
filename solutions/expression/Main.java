package expression;


import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) {
        ExpressionParser pars = new ExpressionParser();
        System.out.println(pars.parse("3-2+5+4").toString());
    }
}
