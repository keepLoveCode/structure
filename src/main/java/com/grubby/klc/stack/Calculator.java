package com.grubby.klc.stack;

import java.util.regex.Pattern;

/**
 * 写一个 有括号 +-x/计算器
 * <p>
 * 使用栈做算法，首先确定要断点
 * 计算器，当出现）时，计算到(  当出现小于当前优先级的符号时，计算前面的数据
 *
 * @author grubby
 * @date 2021/5/23 20:45
 */
public class Calculator {

    private LinkStack<String> numberStack = new LinkStack<>();

    private LinkStack<String> symbolStack = new LinkStack<>();

    public double calculate(String str) {
        String[] strArr = str.split(" ");
        for (String s : strArr) {
            if (isNumber(s)) {
                numberStack.push(s);
            } else {
                while (getPriority(symbolStack.peek()) >= getPriority(s)) {
                    String last = numberStack.pop();
                    String first = numberStack.pop();
                    String calculate = calculate(first, last, symbolStack.pop());
                    numberStack.push(calculate);
                }
                symbolStack.push(s);
            }
        }
        while (symbolStack.peek() != null) {
            String last = numberStack.pop();
            String first = numberStack.pop();
            String calculate = calculate(first, last, symbolStack.pop());
            numberStack.push(calculate);
        }
        return Double.valueOf(numberStack.pop());
    }

    private int getPriority(String symbol) {
        if (symbol == null) {
            return -1;
        }
        switch (symbol) {
            case "/":
            case "*":
                return 2;
            case "-":
            case "+":
                return 1;
        }
        return 0;
    }

    private String calculate(String first, String last, String symbol) {
        Integer firstN = Integer.valueOf(first);
        Integer lastN = Integer.valueOf(last);
        switch (symbol) {
            case "*":
                return String.valueOf(firstN * lastN);
            case "/":
                return String.valueOf(firstN / lastN);
            case "-":
                return String.valueOf(firstN - lastN);
            case "+":
                return String.valueOf(firstN + lastN);
        }
        return "";
    }


    private boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(s).matches();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String s = "12 + 2 / 2 * 20 / 5 - 10 / 2 + 5 * 2 - 1";
        double calculate = calculator.calculate(s);
        System.out.println(calculate);
    }
}
