import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Postfix {
    static double evaluate(String[] expression) {
        Stack<String> S = new Stack<>(expression.length);
        for (int i = 0; i < expression.length; i++) {
            if (expression[i].equals("/")) {
                double num1 = Double.parseDouble(S.pop());
                double num2 = Double.parseDouble(S.pop());
                S.push(num2 / num1 + "");
            } else if (expression[i].equals("*")) {
                double num1 = Double.parseDouble(S.pop());
                double num2 = Double.parseDouble(S.pop());
                S.push(num1 * num2 + "");
            } else if (expression[i].equals("+")) {
                double num1 = Double.parseDouble(S.pop());
                double num2 = Double.parseDouble(S.pop());
                S.push(num1 + num2 + "");
            } else if (expression[i].equals("-")) {
                double num1 = Double.parseDouble(S.pop());
                double num2 = Double.parseDouble(S.pop());
                S.push(num2 - num1 + "");
            } else {
                S.push((expression[i]));
            }
        }
        return Double.parseDouble(S.pop());
    }

    public static void main(String[] args) {
        String[] test1 = {"5", "2", "+"};
        String[] test2 = {"1", "2", "-"};
        String[] test3 = {"4", "5", "*", "3", "+"};
        String[] test4 = {"1", "2", "+", "3", "4", "+", "/"};
        String[] test5 = {"1","2","3","4","5","6","7", "-"};
        String[] test6 = {"1","2","3","4","5","6","7", "8"};
        String[] test7 = {"1","2","+","4","5","-", "/", "/"};
        String[] test8 = {"1", "4", "-", "3", "4", "+", "/"};
        System.out.println(evaluate(test1));
        System.out.println(evaluate(test2));
        System.out.println(evaluate(test3));
        System.out.println(evaluate(test4));


    }
}
