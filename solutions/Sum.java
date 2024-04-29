public class Sum{
    public static void main(String[] args) {
        int sum = 0;
        for (String arg : args) {
            StringBuilder sb = new StringBuilder();
            for (char c : arg.toCharArray()) {
                if (Character.isDigit(c) || c == '-') {
                    sb.append(c);
                } else if (!sb.isEmpty()) {
                    sum += Integer.parseInt(sb.toString());
                    sb.setLength(0);
                }
            }
            if (!sb.isEmpty()) {
                sum += Integer.parseInt(sb.toString());
            }
        }
        System.out.println(sum);
    }
}
