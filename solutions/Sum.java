public class Sum{
    public static void main(String[] args){
        int sum = 0;
        for(String i: args){ 
            String start = "";
            for(int j = 0; j < i.length(); j++){ 
                String element = Character.toString(i.charAt(j));
                if("-0123456789".contains(element)){ 
                    start += element;
                    if(j == i.length() - 1){
                        sum += Integer.parseInt(start);
                        start = "";
                    }
                }else if(start != ""){
                    sum += Integer.parseInt(start);
                    start = "";
                }   
            }
        }System.out.println(sum);
    }
}
