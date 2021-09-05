package cn.element.leetcode.stage5;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 根据 逆波兰表示法，求表达式的值。
 * 有效的算符包括+、-、*、/。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 * 示例1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 *
 * 示例2：
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 *
 * 示例3：
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：
 * 该算式转化为常见的中缀算术表达式为：
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class L150EvaluateReversePolishNotation {

    /**
     * 使用栈
     */
    public int evalRPN(String[] tokens) {

        Deque<Integer> stack = new LinkedList<>();

        int n = tokens.length;

        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Integer.valueOf(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }

        return stack.pop();
    }

    public boolean isNumber(String token) {

        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    public static void main(String[] args) {

        L150EvaluateReversePolishNotation a = new L150EvaluateReversePolishNotation();

        String[] tokens = {"2","1","+","3","*"};

        System.out.println(a.evalRPN(tokens));
    }
}
