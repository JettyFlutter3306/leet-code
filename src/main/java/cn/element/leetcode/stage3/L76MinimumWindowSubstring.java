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

        for (int i = 0; i < tLen; i++) {  //遍历字符串t
            char ch = t.charAt(i);

            ori.put(ch,ori.getOrDefault(ch,0) + 1);  //记录字符串t每个字符出现的次数
        }

        int l = 0;  //左边界
        int r = -1;  //右边界
        int len = Integer.MAX_VALUE;
        int ansL = -1;
        int ansR = -1;

        int sLen = s.length();  //获取字符串s的长度

        while(r < sLen){  //遍历字符串s
            r++;  //右窗口往右滑动

            if(r < sLen && ori.containsKey(s.charAt(r))){  //哈希表ori包含字符串在r位置上的字符
                cnt.put(s.charAt(r),cnt.getOrDefault(s.charAt(r),0) + 1);  //用哈希表cnt记录字符出现的次数
            }

            while(this.check() && l <= r){
                if(r - l + 1 < len){
                    len = r - l + 1;  //计算滑动窗口的长度
                    ansL = l;
                    ansR = l + len;
                }

                if(ori.containsKey(s.charAt(l))){
                    cnt.put(s.charAt(l),cnt.getOrDefault(s.charAt(l),0) - 1);
                }

                l++;  //窗口左边界右移
            }
        }

        return ansL == -1 ? "" : s.substring(ansL,ansR);
    }

    /**
     * 检查哈希表包含字符的个数是否相等,不等则返回false
     * 表示窗口是否包含目标串
     */
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
