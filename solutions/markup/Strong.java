package markup;

import java.util.List;
public class Strong extends TextMarkup{
    public Strong (List<Elements> list){
        super(list, "__", "b");
    }
}