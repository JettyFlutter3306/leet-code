package cn.element.leetcode.stage1;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 */
public class L22GenerateParentheses {

    //法一,暴力解法,还是递归
    public List<String> generateParenthesis(int n) {

        List<String> collector = new ArrayList<>();

//        generateAll(new char[2*n],0,collector);

        backTrack(collector,new StringBuilder(),0,0,n);

        return collector;
    }

    public void generateAll(char[] current,int pos,List<String> result){

        if(pos == current.length){
            if(valid(current)){
                result.add(new String(current));
            }
        }else{
            current[pos] = '(';
            generateAll(current,pos+1,result);

            current[pos] = ')';
            generateAll(current,pos+1,result);
        }
    }

    private boolean valid(char[] current) {

        int balance = 0;

        for (char c : current) {
            if(c == '('){
                balance++;
            }else{
                balance--;
            }

            if(balance < 0){
                return false;
            }
        }

        return balance == 0;
    }

    //法二,回溯法
    public void backTrack(List<String> collector,StringBuilder sb,int open,int close,int max){

        if(sb.length() == max * 2){
            collector.add(sb.toString());

            return;
        }

        if(open < max){
            sb.append('(');

            backTrack(collector,sb,open+1,close,max);

            sb.deleteCharAt(sb.length() - 1);
        }

        if(close < open){
            sb.append(')');

            backTrack(collector,sb,open,close+1,max);

            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {

        L22GenerateParentheses a = new L22GenerateParentheses();

        System.out.println(a.generateParenthesis(3));

    }
}
