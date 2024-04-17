import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.io.*;

public class ReverseMinR {
    public static void main(String[] args) {
        try (BufferedReader system = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            int[][] fin = new int[5][];
            int lenfin = 0;
            while (system.ready()) {
                if (lenfin == fin.length) {
                    fin = Arrays.copyOf(fin, fin.length * 2);
                }
                Scanner line = new Scanner(system.readLine() + " ");
                int[] buffer = new int[5];
                int counter = 0;
                while (line.hasNextInt()) {
                    if (counter == buffer.length) {
                        buffer = Arrays.copyOf(buffer, buffer.length * 2);
                    }
                    buffer[counter] = line.nextInt();
                    counter++;
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
                    System.out.print(mn);
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }
}