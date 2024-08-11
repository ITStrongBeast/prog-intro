import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            int[][] result = new int[5][];
            int sizeResult = 0;
            while (in.ready()) {
                if (sizeResult == result.length) {
                    int[][] buffer = new int[result.length * 2][];
                    System.arraycopy(result, 0, buffer, 0, result.length);
                    result = buffer;
                }
                StringReader line = new StringReader(in.readLine());
                int[] buffer = new int[5];
                int counter = 0;
                while (line.ready()) {
                    if (counter == buffer.length) {
                        buffer = Arrays.copyOf(buffer, buffer.length * 2);
                    }
                    buffer[counter] = line.read();
                    counter++;
                }
                buffer = Arrays.copyOf(buffer, counter);
                result[sizeResult] = buffer;
                sizeResult++;
            }
            System.arraycopy(result, 0, result, 0, sizeResult);
            for (int i = result.length - 1; i >= 0; i--) {
                for (int j = result[i].length - 1; j >= 0; j--) {
                    System.out.print(result[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}
