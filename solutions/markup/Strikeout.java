package markup;

import java.util.List;

public class Strikeout extends TextMarkup{
    
    public Strikeout (List<Elements> list) {
        super(list, "~", "s");
    }
}
