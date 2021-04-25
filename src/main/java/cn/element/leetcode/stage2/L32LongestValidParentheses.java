package cn.element.leetcode.stage2;

import java.util.Stack;

/**
 * 给你一个只包含 '('和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 *
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 *
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 */
public class L32LongestValidParentheses {

    public int longestValidParentheses(String s) {

        Stack<Integer> stack = new Stack<Integer>(){{
            push(-1);
        }};

        int maxLength = 0;

        int n = s.length();

        for (int i = 0; i < n; i++) {
            if(s.charAt(i) == '('){
                stack.push(i);
            }else{
                stack.pop();

                if(stack.empty()){
                    stack.push(i);
                }else{
                    maxLength = Math.max(maxLength,i - stack.peek());
                }
            }
        }

        return maxLength;
    }


    public static void main(String[] args) {

        L32LongestValidParentheses a = new L32LongestValidParentheses();

        String s = ")()())";

        System.out.println(a.longestValidParentheses(s));

    }
}
