package cn.element.leetcode.stage1;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例2：
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 */
public class L20ValidParentheses {

    public boolean isValid(String s) {

        Map<Character,Character> map = new HashMap<Character, Character>(){{
           put(')','(');
           put(']','[');
           put('}','{');
        }};

        Stack<Character> stack = new Stack<>();

        int n = s.length();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if(ch == '(' || ch == '[' || ch == '{'){
                stack.push(ch);
            }

            if(ch == ')' || ch == ']' || ch == '}'){
                if(i == 0 || stack.size() == 0){
                    return false;
                }

                if(stack.peek() != map.get(ch)){
                    return false;
                }else{
                    stack.pop();
                }
            }
        }

        return stack.empty();
    }

    public boolean isValid1(String s) {
        Stack<Character>stack = new Stack<Character>();

        for(char c: s.toCharArray()){

            if(c == '('){
                stack.push(')');
            }else if(c == '['){
                stack.push(']');
            }else if(c == '{'){
                stack.push('}');
            }else if(stack.empty() || c != stack.pop()){
                return false;
            }
        }

        return stack.empty();
    }


    public static void main(String[] args) {

        String s = "{[]}";

        L20ValidParentheses a = new L20ValidParentheses();

        System.out.println(a.isValid(s));

    }
}
