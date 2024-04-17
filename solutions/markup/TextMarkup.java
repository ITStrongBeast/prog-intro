package markup;

import java.util.List;
public abstract class TextMarkup extends Elements {
    private  final List<Elements> list;
    private  final String teg;
    private  final String teg1;

    public TextMarkup (List<Elements> list, String tegs, String tegs1){
        this.list = list;
        this.teg = tegs;
        this.teg1 = tegs1;
    }
    @Override
    public void toMarkdown(StringBuilder str) {
        str.append(teg);
        for (Elements element : list) {
            element.toMarkdown(str);
        }
        str.append(teg);
    }
    @Override
    public void toBBCode(StringBuilder str) {
        str.append("[");
        str.append(teg1);
        str.append("]");
        for (Elements element : list) {
            element.toBBCode(str);
        }
        str.append("[");
        str.append('/');
        str.append(teg1);
        str.append("]");
    }
}