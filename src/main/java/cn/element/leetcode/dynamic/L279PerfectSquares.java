package cn.element.leetcode.dynamic;

/**
 * 给定正整数n，找到若干个完全平方数（比如1, 4, 9, 16, ...）使得它们的和等于 n。
 * 你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
 * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 */
public class L279PerfectSquares {

    /**
     * 我们可以依据题目的要求写出状态表达式：f[i] 表示最少需要多少个数的平方来表示整数 i。
     * 这些数必然落在区间 [1, sqrt{n}]
     * 我们可以枚举这些数，假设当前枚举到 j，那么我们还需要取若干数的平方，构成 i-j^2
     * 此时我们发现该子问题和原问题类似，只是规模变小了。这符合了动态规划的要求，于是我们可以写出状态转移方程。
     * f[i] = 1 + min f[i-j^2] from j = 1 to √i
     * 其中 f[0]=0 为边界条件，实际上我们无法表示数字 0，只是为了保证状态转移过程中遇到 j 恰为√i的情况合法。
     * 同时因为计算f[i]时所需要用到的状态仅有 f[i-j²]，必然小于 i，因此我们只需要从小到大地枚举 i 来计算 f[i] 即可。
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int temp = Integer.MAX_VALUE;

            for (int j = 1; j * j <= i; j++) {
                temp = Math.min(temp, dp[i - j * j]);
            }

            dp[i] = temp + 1;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        L279PerfectSquares a = new L279PerfectSquares();

        System.out.println(a.numSquares(12));
    }
}
