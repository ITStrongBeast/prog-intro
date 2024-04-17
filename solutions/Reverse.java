import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Reverse {
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

            for (int i = fin.length - 1; i >= 0; i--) {
                for (int j = fin[i].length - 1; j >= 0; j--) {
                    System.out.print(fin[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }
}





















/*
import java.util.Scanner;
import java.util.Arrays;

public class Reverse{
    public static void main(String[] args){
        Scanner valera = new Scanner(System.in);
        String[] start = new String[0];
        while (valera.hasNextLine()){
            start = append(start, valera.nextLine());
        }  
        valera.close();
        int[][] fin = new int[start.length][];
        for (int i = 0; i < start.length; i++){
            Scanner egor1 = new Scanner(start[i]);
            int k = 0;
            while (egor1.hasNextInt()){
                egor1.nextInt();
                k += 1;
            }
            fin[i] = new int[k];
            egor1.close();
            Scanner egor2 = new Scanner(start[i]);
            for (int j = 0; j < k; j++){
                fin[i][j] = egor2.nextInt();
            }
            egor2.close()
        }
        for (int i = start.length - 1; i >= 0; i--){
            for (int j = fin[i].length - 1; j >= 0; j--){
                System.out.print(fin[i][j]);
                System.out.print(" ");
            }
            System.out.println();    
        }  
    }

    public static String[] append(String[] len, String element){
        String[] line = new String[len.length + 1];
        System.arraycopy(len, 0, line, 0, len.length);
        line[len.length] = element;
        return line;
    }

}
*/





/*  public static int row(String elem){ 
        int[] buffer = new int[0];
        StringBuilder star = new StringBuilder();
        for(int j = 0; j < elem.length(); j++){ 
            if(!isWhitespace(elem.charAt(j))){
                star.append(elem.charAt(j));
                if(j == elem.length() - 1){
                    buffer = append2(buffer, Integer.parseInt(star.toString()));
                    star.setLength(0);
                }
            }else if(star.length() != 0){
                buffer = appand2(buffer, Integer.parseInt(star.toString()));
                star.setLength(0);
            }
        }
        return buffer;
    }
}*/