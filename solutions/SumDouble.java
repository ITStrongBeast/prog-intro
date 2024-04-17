import static java.lang.Character.isWhitespace;

public class SumDouble{
    public static void main(String[] args){
        double sum = 0;
        for(String i: args){ 
            StringBuilder start = new StringBuilder();
            for(int j = 0; j < i.length(); j++){ 
                if(!isWhitespace(i.charAt(j))){
                    start.append(i.charAt(j));
                    if(j == i.length() - 1){
                        sum += Double.parseDouble(start.toString());
                        start.setLength(0);
                    }
                }else if(start.length() != 0){
                    sum += Double.parseDouble(start.toString());
                    start.setLength(0);
                }   
            }
        }
        System.out.println(sum);
    }
}