package md2html;

import  java.io.*;
import  java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.Character.isWhitespace;

public class Md2Html {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))){
            try (BufferedWriter writter = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
                StringBuilder inp = new StringBuilder();
                Map<String,String> rem = new HashMap<>();
                rem.put("*", "em>");
                rem.put("_", "em>");
                rem.put("**", "strong>");
                rem.put("__", "strong>");
                rem.put("''", "q>");
                rem.put("`", "code>");
                rem.put("--", "s>");
                rem.put("<", "&lt;");
                rem.put(">", "&gt;");
                rem.put("&", "&amp;");
                boolean flag = false;
                while (reader.ready()) {
                    String line = reader.readLine();
                    int i = 0;
                    if (!line.isEmpty() && line.charAt(0) == '#') {
                        while (i < 7) {
                            i++;
                            if (line.charAt(i) != '#') {
                                if (line.charAt(i) == ' ') {
                                    flag = true;
                                }
                                break;
                            }
                        }
                    }
                    if (flag) {
                        inp.append(line);
                        while (reader.ready()) {
                            line = reader.readLine();
                            if (line.isEmpty()) {
                                break;
                            }
                            inp.append(System.lineSeparator());
                            inp.append(line);
                        }
                        writter.write("<h" + i + ">");
                        writter.write(remake(inp.substring(i + 1), rem));
                        writter.write("</h" + i + ">");
                        writter.write(System.lineSeparator());
                        inp.setLength(0);
                        flag = false;
                    } else if (!line.isEmpty()) {
                        inp.append(line);
                        while (reader.ready()) {
                            line = reader.readLine();
                            if (line.isEmpty()) {
                                break;
                            }
                            inp.append(System.lineSeparator());
                            inp.append(line);
                        }
                        writter.write("<p>");
                        writter.write(remake(inp.toString(), rem));
                        writter.write("</p>");
                        writter.write(System.lineSeparator());
                        inp.setLength(0);
                    }
                }
            } catch (IOException e) {
                System.err.println("Где файлы?" + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Где файлы?" + e.getMessage());
        }
    }

    private static String remake(String str, Map<String,String> rem) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder ret = new StringBuilder();
        StringBuilder abc = new StringBuilder();
        stack.add("(*_*)");
        int i = 0;
        while (i < str.length()) {
            String b = "";
            String a = String.valueOf(str.charAt(i));
            abc.append(a);
            if (i < str.length() - 1) {
                b = String.valueOf(str.charAt(i + 1));
                abc.append(b);
            }
            String ab = abc.toString();
            abc.setLength(0);
            if (a.equals("\\")) {
                if (i < str.length() - 2) {
                    abc.append(b);
                    abc.append(str.charAt(i + 2));
                }
                if (!abc.isEmpty() && rem.containsKey(abc.toString())) {
                    ret.append(abc);
                    i += 2;
                } else if (rem.containsKey(b)) {
                    ret.append(b);
                    i += 1;
                }
                abc.setLength(0);
            } else if ((ab.equals(stack.getLast()) || (a.equals(stack.getLast()) && !rem.containsKey(ab))) && str.charAt(i - 1) != ' ') {
                ret.append("</");
                if (ab.equals(stack.getLast())) {
                    ret.append(rem.get(ab));
                    i++;
                } else {
                    ret.append(rem.get(a));
                }
                stack.remove(stack.size() - 1);
            } else if ((rem.containsKey(ab) && i < str.length() - 2 && !isWhitespace(str.charAt(i + 2))) ||
                    (rem.containsKey(a) && i < str.length() - 1 && !isWhitespace(str.charAt(i + 1)))) {
                if (a.equals("<") || a.equals(">") || a.equals("&")) {
                    ret.append(rem.get(a));
                } else {
                    ret.append('<');
                    if (rem.containsKey(ab)) {
                        ret.append(rem.get(ab));
                        stack.add(ab);
                        i++;
                    } else {
                        ret.append(rem.get(a));
                        stack.add(a);
                    }
                }
            } else {
                ret.append(a);
            }
            i++;
        }
        return ret.toString();
    }
}