package cn.element.leetcode.stage1;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串""。
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class L14LongestCommonPrefix {

    //横向扫描法
    public String longestCommonPrefix(String[] strs) {

        if(strs == null || strs.length == 0){
            return "";
        }

        String first = strs[0];

        int len = strs.length;

        for (int i = 1; i < len; i++) {
            first = longestCommonPrefix(first,strs[i]);

            if(first.length() == 0){
                break;
            }
        }

        return first;
    }

    //求出最长公共子串
    public String longestCommonPrefix(String s1,String s2){

        int length = Math.min(s1.length(),s2.length());

        int index = 0;

        while (index < length && s1.charAt(index) == s2.charAt(index)){
            index++;
        }

        return s1.substring(0,index);
    }

    //纵向扫描法
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int length = strs[0].length();
        int count = strs.length;

        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);

            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }

    public static void main(String[] args) {

        L14LongestCommonPrefix a = new L14LongestCommonPrefix();

        String[] strs = {"c","acc","vaac"};

        System.out.println(a.longestCommonPrefix(strs));
    }
}
