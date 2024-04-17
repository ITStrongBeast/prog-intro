package markup;

import java.util.List;
public class Text extends Elements{
    String string;
    public Text(String st) {
        this.string = st;
    }
    @Override
    public void toMarkdown(StringBuilder str) {
        str.append(this.string);
    }

    @Override
    public void toBBCode(StringBuilder str) {
        str.append(this.string);
    }
}