import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class WordStatCountPrefixL{
    public static void main(String[] args){
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
            StringBuilder start = new StringBuilder();
            String[] fin = new String[5]; 
            int[] quantity = new int[5];
            int counter = 0;
            while (in.ready()) {
                String line = in.readLine() + " ";
                for (int i = 0; i < line.length(); i++){
                    char el = line.charAt(i);
                    if ((Character.isLetter(el) || (Character.getType(el) == Character.DASH_PUNCTUATION) ||
                            (el == '\'')) && !Character.isWhitespace(el)) {
                        start.append(String.valueOf(el));
                    } else if (start.length() >= 3) {
                        String str = start.toString().toLowerCase().substring(0,3);
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
                    } else {
                        start.setLength(0);
                    } 
                }
            }
            fin = Arrays.copyOf(fin, counter);
            quantity = Arrays.copyOf(quantity, counter);    
            int transfer;
            String buffer = "";
            for (int i = 0; i <= counter - 1; i++) {
                for (int j = 0; j < counter - 1 - i; j++) {
                    if (quantity[j] > quantity[j+1]) {
                        transfer = quantity[j];
                        quantity[j] = quantity[j+1];
                        quantity[j+1] = transfer;
                        buffer = fin[j];
                        fin[j] = fin[j+1];
                        fin[j+1] = buffer;
                    }                         
                }
            }
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