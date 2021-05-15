package cn.element.leetcode.stage2;

/**
 * 给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 *
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：-1
 *
 * 示例 5：
 * 输入：nums = [-100000]
 * 输出：-100000
 */
public class L53MaximumSubarray {

    /**
     * 贪心算法
     */
    public int maxSubArray(int[] nums) {

        int currentSum = nums[0];  //声明当前和
        int maxSum = nums[0];  //最大和作为结果返回

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i],currentSum + nums[i]);  //比较nums[i]和currentSum+nums[i]大小,如果nums[i]较大那么就把之前的和丢弃

            maxSum = Math.max(currentSum,maxSum);  //更新maxSum的值
        }

        return maxSum;
    }

    public static void main(String[] args) {

        L53MaximumSubarray a = new L53MaximumSubarray();

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(a.maxSubArray(nums));

    }
}
