package cn.element.leetcode.stage3;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 示例 1：
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 示例 2：
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 */
public class L63UniquePathsII {

    /**
     * 考虑动态规划
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if(obstacleGrid[0][0] == 1){
            return 0;
        }

        if(m == 1 && n == 1){
            if(obstacleGrid[0][0] == 0){
                return 1;
            }
        }

        int[][] dp = new int[m][n];

        for (int i = 1; i < n; i++) {  //设置边界条件
            if(obstacleGrid[0][i] == 1){
                break;
            }else{
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            if(obstacleGrid[i][0] == 1){
                break;
            }else{
                dp[i][0] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {

        int n = obstacleGrid.length, m = obstacleGrid[0].length;

        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }

                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }

        return f[m - 1];
    }

    public static void main(String[] args) {

        L63UniquePathsII a = new L63UniquePathsII();

        int[][] obstacleGrid = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };

        System.out.println(a.uniquePathsWithObstacles(obstacleGrid));

    }


}
