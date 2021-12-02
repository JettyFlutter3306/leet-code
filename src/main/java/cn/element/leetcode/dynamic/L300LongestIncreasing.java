package cn.element.leetcode.dynamic;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 *
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 */
public class L300LongestIncreasing {

    /**
     * 动态规划:
     *     定义dp[i]为考虑前i个元素,以第i个数字结尾的最长上升子序列的长度,注意nums[i]必须要被选中
     *     从小到大计算dp数组中的值,在计算dp[i]之前,已经计算除了dp[0...i-1]的值,则状态转移方程则为
     *     dp[i] = max(dp[j]) + 1,其中 0 <= j < i,且num[j] < num[i]
     *     即考虑往dp[0...i-1]中最长的上升子序列后面再加上一个nums[i],由于dp[j]代表nums[0...j]中
     *     以nums[j]结尾的最长上升子序列,所以如果能从dp[j]这个状态转移过来,那么nums[i]必然要大于
     *     最后,整个数组的最长上升子序列即为dp[i]中的最大值
     *          LIS(length) = max(dp[i]), 0 <= i < n
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];

        dp[0] = 1;
        int ans = 1;

        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        L300LongestIncreasing a = new L300LongestIncreasing();

        int[] nums = {10,9,2,5,3,7,101,18};

        System.out.println(a.lengthOfLIS(nums));
    }
}
