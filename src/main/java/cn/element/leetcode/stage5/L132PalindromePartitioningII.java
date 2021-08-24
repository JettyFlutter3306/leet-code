package cn.element.leetcode.stage5;

import java.util.Arrays;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * 返回符合要求的 最少分割次数 。
 *
 * 示例 1：
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * 示例 2：
 * 输入：s = "a"
 * 输出：0
 *
 * 示例 3：
 * 输入：s = "ab"
 * 输出：1
 */
public class L132PalindromePartitioningII {

    /**
     * 考虑使用动态规划
     * 设 f[i] 表示字符串的前缀s[0...i]的最少分割次数,要想得出 f[i] 的值,我们可以考虑枚举 s[o...i] 分割出的最后一个回文串,
     * 这样我们就可以写出状态转移方程 :
     *      f[i] = min {f[j]} + 1 (0 <= j < i), 其中s[j + 1, ..i]是一个回文串
     */
    public int minCut(String s) {

        int n = s.length();
        boolean[][] g = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], true);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];

        Arrays.fill(f, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            if(g[0][i]){
                f[i] = 0;
            }else{
                for (int j = 0; j < i; j++) {
                    if(g[j + 1][i]){
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }

    public static void main(String[] args) {

        L132PalindromePartitioningII a = new L132PalindromePartitioningII();

        String s = "aab";

        System.out.println(a.minCut(s));
    }
}
