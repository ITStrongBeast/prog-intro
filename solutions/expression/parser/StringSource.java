package expression.parser;

import expression.TripleExpression;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class StringSource extends BaseParser {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        super(null);
        System.out.println(data);
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        skipWhitespace();
        return data.charAt(pos++);
    }

    @Override
    public IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(pos + ": " + message);
    }

    private void skipWhitespace() {
        while (take(' ') || take('\r') || take('\n') || take('\t')) {
            // skip
        }
    }
}
