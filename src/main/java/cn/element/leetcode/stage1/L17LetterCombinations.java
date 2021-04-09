package cn.element.leetcode.stage1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 */
public class L17LetterCombinations {

    private final Map<Character,String> map = new HashMap<Character, String>(){{
       put('2',"abc");
       put('3',"def");
       put('4',"ghi");
       put('5',"jkl");
       put('6',"mno");
       put('7',"pqrs");
       put('8',"tuv");
       put('9',"wxyz");
    }};

    public List<String> letterCombinations(String digits) {

        List<String> list = new ArrayList<>();

        if("".equals(digits)){
            return list;
        }

        backTrack(list,digits,0,new StringBuilder());

        return list;
    }

    public void backTrack(List<String> collector,String digits,int index,StringBuilder combination){

        if(index == digits.length()){
            collector.add(combination.toString());
        }else{
            char digit = digits.charAt(index);

            String letters = map.get(digit);

            int letterCount = letters.length();

            for (int i = 0; i < letterCount; i++) {
                combination.append(letters.charAt(i));

                backTrack(collector,digits,index+1,combination);

                combination.deleteCharAt(index);
            }
        }
    }


    public static void main(String[] args) {

        L17LetterCombinations a = new L17LetterCombinations();

        System.out.println(a.letterCombinations("23"));

    }


}
