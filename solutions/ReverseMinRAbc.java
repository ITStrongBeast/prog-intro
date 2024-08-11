import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.io.*;

public class ReverseMinRAbc{
    private static String abc = "abcdefghij";

    private static String code(int mini){
        String strmn = Integer.toString(mini);
        StringBuilder cod = new StringBuilder();
        if (strmn.charAt(0) == '-'){
            cod.append(strmn.charAt(0));
        } else {
            cod.append(abc.charAt(Integer.parseInt(String.valueOf(strmn.charAt(0)))));
        }
        for (int l = 1; l < strmn.length(); l++){
            cod.append(abc.charAt(Integer.parseInt(String.valueOf(strmn.charAt(l)))));
        }
        return new String(cod);
    }

    public static void main(String[] args){
        try (BufferedReader system = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            int[][] fin = new int[5][];
            int lenfin = 0;
            while (system.ready()) {
                if (lenfin == fin.length) {
                    fin = Arrays.copyOf(fin, fin.length * 2);
                }
                String line = system.readLine() + " ";
                int[] buffer = new int[5];
                int counter = 0;
                StringBuilder decode = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '-') {
                        decode.append(line.charAt(i));
                        continue;
                    }
                    if (abc.indexOf(line.charAt(i)) != -1) {
                        decode.append(Integer.toString(abc.indexOf(line.charAt(i))));
                    } else if (decode.length() != 0) {
                        if (counter == buffer.length) {
                            buffer = Arrays.copyOf(buffer, buffer.length * 2);
                        }
                        buffer[counter] = Integer.parseInt(decode.toString());
                        decode.setLength(0);
                        counter++;
                    }
                }
                buffer = Arrays.copyOf(buffer, counter);
                fin[lenfin] = buffer;
                lenfin++;
            }
            fin = Arrays.copyOf(fin, lenfin);
            for (int i = 0; i < fin.length; i++) {
                int mn = Integer.MAX_VALUE;
                for (int j = 0; j < fin[i].length; j++) {
                    mn = Math.min(mn, fin[i][j]);
                    System.out.print(code(mn));
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
