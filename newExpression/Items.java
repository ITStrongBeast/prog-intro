package expression;

import java.util.Objects;

public abstract class Items implements MyExpression {
    private final String strValue;

    protected Items(String strValue) {
        this.strValue = strValue;
    }

    @Override
    public String toString() {
        return strValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Items items)) return false;
        return Objects.equals(strValue, items.strValue);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(strValue);
    }
}
