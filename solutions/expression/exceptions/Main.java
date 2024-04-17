package expression.exceptions;

public class Main {
    public static void main(String[] args) {
        try {
            ExpressionParser par = new ExpressionParser();
            System.out.println(par.parse("1000000*x*x*x*x*x/(x-1)").toString());
        } catch (ParseExceptions e){
            System.out.println(e);
        }
    }
}