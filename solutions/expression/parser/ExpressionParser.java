package expression.parser;

import expression.*;

public class ExpressionParser extends BaseParser{

    public ExpressionParser() {
        super(null);
    }

    private StringSource in = new StringSource("");

    public Unification parse(String expression) {
        in = new StringSource(expression);
        return parseE();
    }

    private Unification parseE() {
        Unification first = parseT();
        while (in.hasNext()) {
            String operator = String.valueOf(in.next());
            if (operator.equals("+")) {
                first = new Add(first, parseT());
            } else if (operator.equals("-")){
                first = new Subtract(first, parseT());
            } else {
                break;
            }
        }
        return first;
    }

    private Unification parseT() {
        Unification first = parseF();
        while (in.hasNext()) {
            String operator = String.valueOf(in.next());
            if (operator.equals("*")) {
                first = new Multiply(first, parseF());
            } else if (operator.equals("/")) {
                first = new Divide(first, parseF());
            } else {
                break;
            }
        }
        return first;
    }

    private Unification parseF() {
        String next = String.valueOf(in.next());
        Unification result;
        if (next.equals("(")) {
            take();
            result = parseE();
            String closingBracket = String.valueOf(in.next());
            if (in.hasNext() && closingBracket.equals(")")) {
                take();
                return result;
            }
        }
        return new Const(Integer.parseInt(next));
    }
}




























//    private final Deque<Teg> startStack = new LinkedList<>();
//    private final Deque<Unification> finishStack = new LinkedList<>();
//    private final StringBuilder digit = new StringBuilder();
//    private boolean unarmin = true;
//
//    private enum Teg{
//        UM(60),
//        M(50),
//        D(50),
//        A(40),
//        S(40),
//        BA(30),
//        BX(20),
//        BO(10),
//        LB(0);
//        private final int i;
//        Teg(int i) {
//            this.i = i;
//        }
//        private int getTeg(){
//            return i;
//        }
//    }
//
//    public Unification parse(String start) {
//        int i = 0;
//        while (i < start.length()) {
//            char symbol = start.charAt(i);
//            if (symbol == 'x' || symbol == 'y' || symbol == 'z') { // обработка переменных
//                finishStack.push(new Variable(String.valueOf(symbol)));
//                unarmin = false;
//            } else if (Character.isDigit(symbol)) { // обработка чисел
//                digit.append(symbol);
//                while (i != start.length() - 1 && Character.isDigit(start.charAt(i + 1))) {
//                    i++;
//                    digit.append(start.charAt(i));
//                }
//                finishStack.push(new Const(Integer.parseInt(digit.toString())));
//                unarmin = false;
//                digit.setLength(0);
//            } else if (symbol == '-') { // обработка минуса
//                if (finishStack.isEmpty() || unarmin) { // Унарный
//                    if (i != start.length() - 1 && Character.isDigit(start.charAt(i + 1))) { // при константе
//                        digit.append("-");
//                    } else {
//                        startStack.push(Teg.UM);
//                    }
//                } else {
//                    removeStack("-");
//                }
//            } else if (symbol == '(') {  // обработка скобок
//                startStack.push(Teg.LB);
//            } else if (symbol == ')') {
//                while (true) {
//                    Teg a = startStack.pop();
//                    if (Objects.equals(a, Teg.LB)) break;
//                    conversion(a);
//                }
//            } else if ("+*/&^|".contains(String.valueOf(symbol))) { // обработка бинарных операций
//                removeStack(String.valueOf(symbol));
//            }
//            i++;
//        }
//        while (!startStack.isEmpty()) {
//            conversion(startStack.pop());
//        }
//        return finishStack.pop();
//    }
//
//    private void removeStack(String s) { // метод сортировочной станции
//        unarmin = true;
//        Teg str = translation(s);
//        while (!startStack.isEmpty() && (startStack.peek().getTeg() >= str.getTeg())){
//            conversion(startStack.pop());
//        }
//        startStack.push(str);
//    }
//
//    private void conversion(Teg expression) { // преобразование в объекты
//        Unification stack_element = finishStack.pop();
//        finishStack.push(switch (expression) {
//            case S -> new Subtract(finishStack.pop(), stack_element);
//            case A -> new Add(finishStack.pop(), stack_element);
//            case D -> new Divide(finishStack.pop(), stack_element);
//            case M -> new Multiply(finishStack.pop(), stack_element);
//            case BA -> new BitAnd(finishStack.pop(), stack_element);
//            case BX -> new BitXor(finishStack.pop(), stack_element);
//            case BO -> new BitOr(finishStack.pop(), stack_element);
//            default -> new UnaryMinus(stack_element);
//        });
//    }
//
//    private Teg translation(String str) {
//        return switch (str) {
//            case "-" -> Teg.S;
//            case "+" -> Teg.A;
//            case "*" -> Teg.M;
//            case "/" -> Teg.D;
//            case "&" -> Teg.BA;
//            case "^" -> Teg.BX;
//            case "|" -> Teg.BO;
//            default -> Teg.UM;
//        };
//    }
//}