package cn.element.leetcode.stage3;

import java.util.Arrays;

/**
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 *
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 *
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 */
public class L62UniquePaths {

    /**
     * 考虑使用动态规划
     * 我们用 f(i, j) 表示从左上角走到 (i, j) 的路径数量，其中 i 和 j 的范围分别是 [0, m) 和 [0, n)。
     *
     * 由于我们每一步只能从向下或者向右移动一步，因此要想走到 (i, j)，如果向下走一步，那么会从 (i-1, j) 走过来；
     * 如果向右走一步，那么会从 (i, j-1) 走过来。因此我们可以写出动态规划转移方程：
     *
     * f(i, j) = f(i-1, j) + f(i, j-1)
     *
     * 需要注意的是，如果 i=0i=0，那么 f(i-1,j)f(i−1,j) 并不是一个满足要求的状态，
     * 我们需要忽略这一项；同理，如果 j = 0，那么 f(i,j-1)并不是一个满足要求的状态，我们需要忽略这一项。
     * 初始条件为 f(0,0)=1，即从左上角走到左上角有一种方法。
     * 最终的答案即为 f(m-1,n-1)。
     */
    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];  //创建一个 m+1 * n+1 的二维数组用于保存动态规划结果

        for (int i = 0; i < dp.length; i++) {  //边界条件都设为1
            dp[i][0] = 1;
        }

        Arrays.fill(dp[0], 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];  //使用动态转移方程
            }
        }

        return dp[m - 1][n - 1];  //返回矩阵右下角的数据
    }

    /**
     * 使用组合数
     * 从左上角到右下角的过程中，我们需要移动 m+n-2 次，
     * 其中有 m-1 次向下移动，n-1 次向右移动。
     * 因此路径的总数，就等于从 m+n-2 次移动中选择 m-1 次向下移动的方案数，即组合数：
     */
    public int uniquePaths1(int m,int n){

        long ans = 1;

        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }

        return (int) ans;
    }

    public static void main(String[] args) {

        L62UniquePaths a = new L62UniquePaths();

        System.out.println(a.uniquePaths(3, 7));

    }
}
