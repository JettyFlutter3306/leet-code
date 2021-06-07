package cn.element.leetcode.stage3;

/**
 * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 示例1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class L72EditDistance {

    /**
     * 考虑动态规划,能使用动态规划就千万不要使用递归
     * 我们可以对任意一个单词进行三种操作：
     * 插入一个字符；
     * 删除一个字符；
     * 替换一个字符。
     */
    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        if(n * m == 0){  //有一个串为空串
            return n + m;
        }

        int[][] dp = new int[n + 1][m + 1];  //声明一个动态规划数组

        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i < m + 1; i++) {
            dp[0][i] = i;
        }

        //填表
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = dp[i - 1][j] + 1;
                int down = dp[i][j - 1] + 1;
                int leftDown = dp[i - 1][j - 1];

                if(word1.charAt(i - 1) != word2.charAt(j - 1)){
                    leftDown += 1;
                }

                dp[i][j] = Math.min(left,Math.min(down,leftDown));
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {

        L72EditDistance a = new L72EditDistance();

        String word1 = "horse";
        String word2 = "ros";

        System.out.println(a.minDistance(word1, word2));

    }
}
