package markup;

import java.util.List;
public class Paragraph extends TextMark {
//    private final new HashMap<StringBuilder> map;
    private final List<Elements> list;
    public Paragraph(List<Elements> list){
        this.list = list;
    }
    @Override
    public void toMarkdown(StringBuilder str) {
        for (Elements element : list){
            element.toMarkdown(str);
        }
    }
    @Override
    public void toBBCode(StringBuilder str) {
        for (Elements element : list){
            element.toBBCode(str);
        }
    }
}