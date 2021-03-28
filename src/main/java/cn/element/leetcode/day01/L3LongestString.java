package cn.element.leetcode.day01;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 *
 * 输入: s = ""
 * 输出: 0
 */
public class L3LongestString {

    /**
     * 使用滑动窗口的方法来获得子串
     */
    public static int lengthOfLongestSubstring(String s) {

        Set<Character> set = new HashSet<>();//定义一个hashset用于记录字符是否出现过

        int n = s.length();//定义变量n接收字符串的长度

        int rk = -1,ans = 0;//右指针,初始值为-1,相当于我们在字符串左边界的左侧,还没有开始移动,ans指的是子串的长度

        for (int i = 0; i < n; i++) {//遍历整个字符串
            if(i != 0){
                set.remove(s.charAt(i - 1));//左指针向右移动一格,移除一个字符
            }

            while(rk + 1 < n && !set.contains(s.charAt(rk+1))){
                set.add(s.charAt(rk+1));//不断地移动右指针
                ++rk;
            }

            //第i到rk个字符是一个极长的无重复字符子串
            ans = Math.max(ans,rk-i+1);
        }

        return ans;
    }

    public static void main(String[] args) {

        String str = "pwwkew";
        int count = lengthOfLongestSubstring(str);

        System.out.println(count);


    }
}
