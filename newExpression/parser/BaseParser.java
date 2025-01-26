package expression.parser;

public class BaseParser {
    private static final char END = '\0';
    private CharSource source;
    private char ch;

    protected BaseParser() {
    }

    protected void setSource(CharSource source) {
        this.source = source;
        take();
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(final char expected) {
        if (test(expected)) {
            take();
        }
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            ch = source.hasNext() ? source.next() : END;
        }
    }
}
