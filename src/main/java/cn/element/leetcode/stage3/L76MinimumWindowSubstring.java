package cn.element.leetcode.stage3;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 ""
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 */
public class L76MinimumWindowSubstring {

    Map<Character,Integer> ori = new HashMap<>();
    Map<Character,Integer> cnt = new HashMap<>();

    public String minWindow(String s, String t) {

        int tLen = t.length();

        for (int i = 0; i < tLen; i++) {
            char ch = t.charAt(i);

            ori.put(ch,ori.getOrDefault(ch,0) + 1);
        }

        int l = 0;
        int r = -1;
        int len = Integer.MAX_VALUE;
        int ansL = -1;
        int ansR = -1;

        int sLen = s.length();

        while(r < sLen){
            r++;

            if(r < sLen && ori.containsKey(s.charAt(r))){
                cnt.put(s.charAt(r),cnt.getOrDefault(s.charAt(r),0) + 1);
            }

            while(this.check() && l <= r){
                if(r - l + 1 < len){
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }

                if(ori.containsKey(s.charAt(l))){
                    cnt.put(s.charAt(l),cnt.getOrDefault(s.charAt(l),0) - 1);
                }

                l++;
            }
        }

        return ansL == -1 ? "" : s.substring(ansL,ansR);
    }

    public boolean check(){

        for (Map.Entry<Character, Integer> entry : ori.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();

            if(cnt.getOrDefault(key,0) < value){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        L76MinimumWindowSubstring a = new L76MinimumWindowSubstring();

        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(a.minWindow(s, t));
    }
}
