package expression.exceptions;

import expression.*;

import java.util.*;

public class ExpressionParser implements TripleParser {
    private final Deque<Teg> startStack = new ArrayDeque<>();
    private final Deque<Unification> finishStack = new ArrayDeque<>();
    private final StringBuilder digit = new StringBuilder();
    private String last;

    private enum Teg {
        UM(600),
        L0(600),
        T0(600),
        M(500),
        D(500),
        A(400),
        S(400),
        BA(300),
        BX(200),
        BO(100),
        LB(0);
        private final int i;

        Teg(int i) {
            this.i = i;
        }

        private int getTeg() {
            return i;
        }
    }

    public Unification parse(String start) throws ParseExceptions {
        startStack.clear();
        finishStack.clear();
        int k = 0, i = 0;
        last = " ";
        while (i < start.length()) {
            char symbol = start.charAt(i);
            if ((symbol == 'l' || symbol == 't') && i != start.length() - 1 && start.charAt(i + 1) == '0') {
                if (i != start.length() - 2 && ("xyz".contains(String.valueOf(start.charAt(i + 2))))) {
                    throw new OperationException("Incorrect input of the argument for finding zero bits: " + symbol + "0");
                }
                if (symbol == 'l' && start.charAt(i + 1) == '0') {
                    startStack.push(Teg.L0);
                    last = "l";
                } else {
                    startStack.push(Teg.T0);
                    last = "t";
                }
                i++;
            } else if (symbol == 'x' || symbol == 'y' || symbol == 'z') {
                finishStack.push(new Variable(String.valueOf(symbol)));
                last = String.valueOf(symbol);
            } else if (Character.isDigit(symbol)) {
                digit.append(symbol);
                while (i != start.length() - 1 && Character.isDigit(start.charAt(i + 1))) {
                    i++;
                    digit.append(start.charAt(i));
                }
                checkOverflow(digit.toString());
                finishStack.push(new Const(Integer.parseInt(digit.toString())));
                last = digit.toString();
                digit.setLength(0);
            } else if (symbol == '-') {
                if (finishStack.isEmpty() || "+*/-&^|lt~".contains(last)) {
                    if (i != start.length() - 1 && Character.isDigit(start.charAt(i + 1))) {
                        digit.append("-");
                    } else {
                        startStack.push(Teg.UM);
                        last = "~";
                    }
                } else {
                    removeStack("-");
                }
            } else if (symbol == '(') {
                k++;
                startStack.push(Teg.LB);
            } else if (symbol == ')') {
                if ("+-*/&^|".contains(last)) throw new OperationException("There is no object for a binary operation");
                if (k == 0) throw new BracketException("There are not enough left brackets");
                while (true) {
                    Teg a = startStack.pop();
                    if (Objects.equals(a, Teg.LB)) {
                        k--;
                        break;
                    }
                    conversion(a);
                }
            } else if ("+*/&^|".contains(String.valueOf(symbol))) {
                if ("+*/&^|".contains(last)) {
                    throw new OperationException("There is no object for a binary operation");
                }
                removeStack(String.valueOf(symbol));
            } else if (!Character.isWhitespace(symbol)) {
                throw new ParseExceptions("Invalid character in the expression: " + symbol);
            }
            i++;
        }
        if (k > 0) {
            throw new BracketException("There are not enough right brackets");
        }
        if (finishStack.isEmpty())
            throw new ParseExceptions("There are not enough constants and variables in the expression");
        while (!startStack.isEmpty()) {
            conversion(startStack.pop());
        }
        return finishStack.pop();
    }

    private void removeStack(String s) throws ParseExceptions { // метод сортировочной станции
        last = s;
        Teg str = translation(s);
        while (!startStack.isEmpty() && startStack.peek().getTeg() >= str.getTeg()) {
            conversion(startStack.pop());
        }
        startStack.push(str);
    }

    private Teg translation(String str) {
        return switch (str) {
            case "-" -> Teg.S;
            case "+" -> Teg.A;
            case "*" -> Teg.M;
            case "/" -> Teg.D;
            case "&" -> Teg.BA;
            case "^" -> Teg.BX;
            case "|" -> Teg.BO;
            default -> Teg.UM;
        };
    }


    private void conversion(Teg expression) throws ParseExceptions {
        if (finishStack.isEmpty()) throw new OperationException("There is no object for a unary operation");
        Unification stack_element = finishStack.pop();
        switch (expression) {
            case UM -> finishStack.push(new CheckedNegate(stack_element));
            case L0 -> finishStack.push(new HighestZeroBit(stack_element));
            case T0 -> finishStack.push(new LowerZeroBit(stack_element));
            default -> {
                finishStack.push(switch (expression) {
                    case S -> new CheckedSubtract(finishStack.pop(), stack_element);
                    case A -> new CheckedAdd(finishStack.pop(), stack_element);
                    case D -> new CheckedDivide(finishStack.pop(), stack_element);
                    case M -> new CheckedMultiply(finishStack.pop(), stack_element);
                    case BA -> new BitAnd(finishStack.pop(), stack_element);
                    case BX -> new BitXor(finishStack.pop(), stack_element);
                    default -> new BitOr(finishStack.pop(), stack_element);
                });
            }
        }
    }

    private void checkOverflow(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new OperationException("There is no object for a binary operation");
        }
    }
}