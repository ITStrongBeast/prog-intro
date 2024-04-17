import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.io.*;

public class Wspp{
    public static void main(String[] args){
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))){
            int number = 0;
            StringBuilder start = new StringBuilder();
            LinkedHashMap<String, ArrayList<Integer>> hashmap = new LinkedHashMap<>();
            while (in.ready()) {
                String line = in.readLine() + " ";
                for (int i = 0; i < line.length(); i++){
                    char el = line.charAt(i);
                    if (Character.isLetter(el) | (Character.getType(el) == Character.DASH_PUNCTUATION) | (el == '\'')) {
                        start.append(String.valueOf(el));
                    } else if (start.length() != 0){
                        String str = start.toString().toLowerCase();
                        start.setLength(0);
                        number++;
                        if (hashmap.get(str) == null) {
                            hashmap.put(str, new ArrayList<Integer>());
                        }
                        hashmap.get(str).add(number);
                    }
                }
            }
            try (FileWriter writter = new FileWriter(args[1], StandardCharsets.UTF_8)) {
                for (String key : hashmap.keySet()){
                    writter.write(key + " " + hashmap.get(key).size());
                    for (int j : hashmap.get(key)){
                        writter.write(" " + j);
                    }
                    writter.write("\n");
                }
            } catch (IOException e) {
                System.err.println("Input not found: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input not found: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}