import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.io.*;

public class WsppPosition{
    public static void main(String[] args){
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))){
            int number = 0;
            StringBuilder start = new StringBuilder();
            LinkedHashMap<String, ArrayList<Integer>> hashmap = new LinkedHashMap<>();
            while (in.ready()) {
                number++;
                String line = in.readLine() + " ";
                ArrayList<String> len = new ArrayList<String>();
                for (int i = 0; i < line.length(); i++){
                    char el = line.charAt(i);
                    if (Character.isLetter(el) | (Character.getType(el) == Character.DASH_PUNCTUATION) | (el == '\'')) {
                        start.append(String.valueOf(el));
                    } else if (start.length() != 0){
                        String str = start.toString().toLowerCase();
                        start.setLength(0);
                        len.add(str);
                    }
                }
                int counter = 0;
                for (String st : len){
                    counter++;
                    if (hashmap.get(st) == null) { 
                        hashmap.put(st, new ArrayList<Integer>());
                    }
                    hashmap.get(st).add(number);
                    hashmap.get(st).add(len.size() - counter + 1);
                }
            }
            try (BufferedWriter writter = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) { // bufferedwriter
                for (String key : hashmap.keySet()){
                    writter.write(key + " " + (hashmap.get(key).size() / 2));
                    for (int j = 0; j < hashmap.get(key).size(); j += 2){
                        writter.write(" " + hashmap.get(key).get(j) + ":" + hashmap.get(key).get(j+1));
                    }
                    writter.write("\n");
                }
            } catch (IOException e) {
                System.err.println("Input not found: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Input not found: " + e.getMessage());
        }
    }
}