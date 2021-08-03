package cn.element.leetcode.stage4;

/**
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 * 示例1：
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 *
 * 示例2：
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 */
public class L115DistinctSubsequences {

    /**
     * 假设字符串s和t的长度分别为m和n,如果t是s的子序列,则s的长度一定大于或等于t的长度,即只有当m>=n时
     * t才可能是s的子序列,如果m < n则t一定不是s的子序列,因此直接返回0
     * 当 m >= n 时,可以通过动态规划的方法计算在 s 的子序列中 t 出现的个数
     * 创建二维数组 dp ,其中dp[i][j]表示s[i:]的子序列中t[j:]出现的个数
     *
     * 时间复杂度: O(mn) m,n 分别为字符串s,t的长度
     */
    public int numDistinct(String s, String t) {

        int m = s.length();
        int n = t.length();

        if(m < n){
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }

        for (int i = m - 1; i >= 0; i--) {
            char chs = s.charAt(i);

            for (int j = n - 1; j >= 0; j--) {
                char cht = t.charAt(j);

                if(chs == cht){
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                }else{
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {

        L115DistinctSubsequences a = new L115DistinctSubsequences();

        String s = "rabbbit";
        String t = "rabbit";

        System.out.println(a.numDistinct(s, t));
    }
}
