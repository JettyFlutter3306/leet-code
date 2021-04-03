package cn.element.leetcode.stage1;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 输入：s = "a"
 * 输出："a"
 *
 * 输入：s = "ac"
 * 输出："a"
 */
public class L5LongestPalindromicSubstring {

    /**
     * 使用动态规划来解题
     */
    public static String longestPalindrome(String s) {

        int n = s.length();//求出字符串的长度

        boolean[][] dp = new boolean[n][n];//声明一个二维数组用来记录状态

        String str = "";//用于接收最终的字符串

        for (int i = 0; i < n; i++) {
            for (int j = 0; j + i < n; j++) {
                int k = i + j;

                if(i == 0){
                    dp[j][k] = true;
                }else if(i == 1){
                    dp[j][k] = s.charAt(j) == s.charAt(k);
                }else{
                    dp[j][k] = s.charAt(j) == s.charAt(k) && dp[j+1][k-1];
                }

                if(dp[j][k] && i + 1 > str.length()){
                    str = s.substring(j,j + i + 1);
                }
                
            }
        }

        return str;
    }

    public static void main(String[] args) {

        String s = longestPalindrome("babad");

        System.out.println(s);
    }


}
