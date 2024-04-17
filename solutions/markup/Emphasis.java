package markup;

import java.util.List;
public class Emphasis extends TextMarkup{
    public Emphasis (List<Elements> list){
        super(list, "*", "i");
    }
}