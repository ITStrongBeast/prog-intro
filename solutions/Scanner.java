import java.io.*;
import java.nio.charset.StandardCharsets;

public class Scanner{
    private final Reader reader;
    private final char[] buffer = new char[128];
    private final StringBuilder fin = new StringBuilder();
    private int lastin;
    private int counter;

    public Scanner(InputStream inp){
        this.reader = new InputStreamReader(inp, StandardCharsets.UTF_8);
    }

    public Scanner(String in){
        this.reader = new StringReader(in);
    }

    private void update(Reader reader){
        try{
            this.counter = reader.read(this.buffer);
        } catch (IOException e) {
            System.err.println("Input not found: " + e.getMessage());
        }
    }

    public boolean hasNextInt(){
        while (true){
            if (this.lastin == this.counter){
                this.update(this.reader);
                lastin = 0;
                if (this.counter == -1){
                    break;
                }
            }
            for (int i = this.lastin; i < this.counter; i++){
                if (Character.isDigit(this.buffer[i]) || this.buffer[i] == '-'){
                    return true;
                }
                this.lastin++;
            }
        }
        return false;
    }

    public int nextInt(){
        fin.setLength(0);
        while (true){
            if (this.lastin == this.counter){
                this.update(this.reader);
                lastin = 0;
                if (this.counter == -1){
                    break;
                }
            }
            for (int i = this.lastin; i < this.counter; i++){
                if (Character.isDigit(this.buffer[i]) || this.buffer[i] == '-'){
                    this.fin.append(this.buffer[i]);
                } else if (!this.fin.isEmpty()){
                    return Integer.parseInt(new String(fin));
                }
                this.lastin++;
            }
        }
        return Integer.parseInt(new String(fin));
    }

    public boolean hasNextLine(){
        fin.setLength(0);
        while (true){
            if (this.lastin == this.counter){
                this.update(this.reader);
                this.lastin = 0;
                if (this.counter == -1){
                    break;
                }
            }
            for (int i = this.lastin; i < this.counter; i++){
                if (this.buffer[i] != System.lineSeparator().charAt(System.lineSeparator().length() - 1)){
                    this.fin.append(this.buffer[i]);
                } else if (!this.fin.isEmpty()){
                    return true;
                }
                this.lastin++;
            }
        }
        return !fin.isEmpty();
    }

    public String nextLine(){
        return new String(fin);
    }

    public void close(){
        try{
            reader.close();
        } catch (IOException e) {
            System.err.println("Input not found: " + e.getMessage());
        }
    }
}