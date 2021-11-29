package cn.element.leetcode.util;

public class DigitUtil {

    public static int calculate(int num1, char c, int num2) {
        switch (c) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }

        return -1;
    }

    public static boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }
}
