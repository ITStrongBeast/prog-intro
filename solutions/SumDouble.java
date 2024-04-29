import static java.lang.Character.isWhitespace;

public class SumDouble{
    public static void main(String[] args) {
        double sum = 0;
        for (String arg : args) {
            StringBuilder sb = new StringBuilder();
            for (char c : arg.toCharArray()) {
                if (!isWhitespace(c)) {
                    sb.append(c);
                } else if (!sb.isEmpty()) {
                    sum += Double.parseDouble(sb.toString());
                    sb.setLength(0);
                }
            }
            if (!sb.isEmpty()) {
                sum += Double.parseDouble(sb.toString());
            }
        }
        System.out.println(sum);
    }
}
