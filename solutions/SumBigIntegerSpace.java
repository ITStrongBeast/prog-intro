import java.math.BigInteger;

public class SumBigIntegerSpace {
    public static void main(String[] args) {
        BigInteger sum = BigInteger.valueOf(0);
        for (String arg : args) {
            StringBuilder sb = new StringBuilder();
            for (char c : arg.toCharArray()) {
                if (Character.isDigit(c) || c == '-') {
                    sb.append(c);
                } else if (!sb.isEmpty()) {
                    sum = sum.add(new BigInteger(sb.toString()));
                    sb.setLength(0);
                }
            }
            if (!sb.isEmpty()) {
                sum = sum.add(new BigInteger(sb.toString()));
            }
        }
        System.out.println(sum);
    }
}
