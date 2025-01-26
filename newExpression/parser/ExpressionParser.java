package expression.parser;

import expression.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ExpressionParser extends BaseParser implements TripleParser {
    private final Map<String, BiFunction<MyExpression, MyExpression, MyExpression>> creator = new HashMap<>(Map.of(
            "+", Add::new,
            "-", Subtract::new,
            "*", Multiply::new,
            "/", Divide::new,
            "|", BitOr::new,
            "&", BitAnd::new,
            "^", BitXor::new
            ));

    public MyExpression parse(String expression) {
        setSource(new StringSource(expression));
        return parseE();
    }

    private MyExpression parseE() {
        skipWhitespace();
        MyExpression argument = parseT();
        while (true) {
            skipWhitespace();
            if (!test('+') && !test('-')) return argument;
            argument = creator.get(String.valueOf(take())).apply(argument, parseT());
        }
    }

    private MyExpression parseT() {
        skipWhitespace();
        MyExpression argument = parseI();
        while (true) {
            skipWhitespace();
            if (!test('*') && !test('/')) return argument;
            argument = creator.get(String.valueOf(take())).apply(argument, parseI());
        }
    }

    private MyExpression parseI() {
        skipWhitespace();
        if (take('(')) {
            skipWhitespace();
            MyExpression argument = parseE();
            skipWhitespace();
            expect(')');
            return argument;
        }
        if (between('x', 'z')) return new Variable(String.valueOf(take()));
        if (take('-')) return between('0', '9')
                ? new Const(findConst(new StringBuilder("-"))) : new Negate(parseI());
        return new Const(findConst(new StringBuilder()));
    }

    private int findConst(StringBuilder number) {
        do {
            number.append(take());
        } while (between('0', '9'));
        return Integer.parseInt(number.toString());
    }
}
