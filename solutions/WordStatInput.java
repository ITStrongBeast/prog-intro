import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class WordStatInput{
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
            StringBuilder start = new StringBuilder();
            String[] fin = new String[5]; 
            int[] quantity = new int[5];
            int counter = 0;
            while (in.ready()) {
                String line = in.readLine() + " ";
                System.err.println(line);
                for (int i = 0; i < line.length(); i++) {
                    char el = line.charAt(i);
                    if (Character.isLetter(el) || (Character.getType(el) == Character.DASH_PUNCTUATION) || (el == '\'')) {
                        start.append(String.valueOf(el));
                    } else {
                        String str = start.toString().toLowerCase();
                        start.setLength(0);
                        int index = 0;
                        boolean flag = false;
                        for (String elem : fin) {
                            if (str.equals(elem)) {
                                quantity[index]++;
                                flag = true;
                                break;
                            }
                            index++;
                        }
                        if (flag == false & (str != "")) {
                            if (counter == fin.length) {
                                fin = Arrays.copyOf(fin, fin.length * 2);
                                quantity = Arrays.copyOf(quantity, quantity.length * 2);
                            }
                            fin[counter] = str;
                            quantity[counter] = 1;
                            counter++;          
                        }
                    }
                }
            }
            fin = Arrays.copyOf(fin, counter);
            quantity = Arrays.copyOf(quantity, counter);    
            try (FileWriter writter = new FileWriter(args[1], StandardCharsets.UTF_8)){
                for (int i = 0; i < counter; i++) {   
                    writter.write(fin[i] + " " + quantity[i] +"\n");
                } 
            } catch (IOException e) {
                System.err.println("Input not found: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}