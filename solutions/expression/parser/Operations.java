package expression.parser;

import expression.ToMiniString;
import expression.common.ExpressionKind;
import expression.common.Reason;

import java.util.function.Consumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.stream.IntStream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class Operations {
    public static final Operation NEGATE = unary("-", a -> -a);
    @SuppressWarnings("Convert2MethodRef")
    public static final Operation ADD       = binary("+", 1600, (a, b) -> a + b);
    public static final Operation SUBTRACT  = binary("-", 1602, (a, b) -> a - b);
    public static final Operation MULTIPLY  = binary("*", 2001, (a, b) -> a * b);
    public static final Operation DIVIDE    = binary("/", 2002, (a, b) -> b == 0 ? Reason.DBZ.error() : a / b);

    public static final Operation AND = binary("&", 800, (a, b) -> a & b);
    public static final Operation XOR = binary("^", 760, (a, b) -> a ^ b);
    public static final Operation OR = binary("|", 720, (a, b) -> a | b);

    public static final Operation L_ONES = unary("l1", v ->
            (int) IntStream.iterate((int) v, i -> i < 0, i -> i << 1).count());
    public static final Operation T_ONES = unary("t1", v ->
            (int) IntStream.iterate((int) v, i -> (i & 1) != 0, i -> i >>> 1).count());

    public static final Operation HIGH = unary("high", v -> Integer.highestOneBit((int) v));
    public static final Operation LOW = unary("low", v -> Integer.lowestOneBit((int) v));

    public static final Operation NOT = unary("~", a -> ~a);

    public static final Operation L_ZEROES = unary("l0", v -> Integer.numberOfLeadingZeros((int) v));
    public static final Operation T_ZEROES = unary("t0", v -> Integer.numberOfTrailingZeros((int) v));


    private Operations() {
    }

    @FunctionalInterface
    public interface Operation extends Consumer<ParserTester> {}

    public static Operation unary(final String name, final LongUnaryOperator op) {
        return tests -> tests.unary(name, op);
    }

    public static Operation binary(final String name, final int priority, final LongBinaryOperator op) {
        return tests -> tests.binary(name, priority, op);
    }

    public static <E extends ToMiniString, C> Operation kind(
            final ExpressionKind<E, C> kind,
            final ParserTestSet.Parser<E> parser
    ) {
        return factory -> factory.kind(kind, parser);
    }
}
