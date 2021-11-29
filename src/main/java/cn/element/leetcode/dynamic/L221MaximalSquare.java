package cn.element.leetcode.dynamic;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 *
 * 示例 2：
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 *
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 */
public class L221MaximalSquare {

    /**
     * 动态规划状态转移方程
     * 设dp[i][j]为以(i,j)坐标作为右下角正方形的最大边长
     * 那么这个边长就取决于它的左边,上边和左上边的为右下角的正方形的最小值 + 1,也就是整个正方形的阶数
     * 由此可得状态转移方程 dp[i][j] = min(dp[i][j-1],dp[i-1][j-1],dp[i-1][j]) + 1
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        //声明一个容量一样的dp数组
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        int maxSide = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    if (matrix[i][j] == '1') {
                        dp[i][j] = 1;
                    }
                } else if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }

                maxSide = Math.max(dp[i][j], maxSide);
            }
        }

        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        L221MaximalSquare a = new L221MaximalSquare();

        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'},
        };

        System.out.println(a.maximalSquare(matrix));
    }

}
