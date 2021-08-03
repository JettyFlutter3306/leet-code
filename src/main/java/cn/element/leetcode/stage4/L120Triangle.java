package cn.element.leetcode.stage4;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为11（即，2+3+5+1= 11）。
 *
 * 示例 2：
 * 输入：triangle = [[-10]]
 * 输出：-10
 */
public class L120Triangle {

    /**
     * 考虑使用动态规划
     */
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();

        int[][] dp = new int[n][n];

        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);

            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
            }

            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }

        int minTotal = dp[n - 1][0];

        for (int i = 1; i < n; i++) {
            minTotal = Math.min(minTotal, dp[n- 1][i]);
        }

        return minTotal;
    }

    public static void main(String[] args) {

        L120Triangle a = new L120Triangle();

        int[][] value = {
                {2},
                {3,4},
                {6,5,7},
                {4,1,8,3}
        };

        List<List<Integer>> lists = new ArrayList<>();

        for (int[] temp : value) {
            List<Integer> list = new ArrayList<>();

            for (int i : temp) {
                list.add(i);
            }

            lists.add(list);
        }

        System.out.println(a.minimumTotal(lists));
    }
}
