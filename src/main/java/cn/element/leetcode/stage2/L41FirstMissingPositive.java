package cn.element.leetcode.stage2;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
 *
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 *
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 */
public class L41FirstMissingPositive {

    /**
     * 考虑使用哈希表解法
     * @param nums          数组
     * @return              最小缺失正整数
     */
    public int firstMissingPositive(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < n; i++) {  //将分负数和0变成 n + 1
            if(nums[i] <= 0){
                nums[i] = n + 1;
            }
        }

        for (int i = 0; i < n; i++) {  //把小于等于 n 的对应的数变成负数
            int num = Math.abs(nums[i]);

            if(num <= n){
                nums[num-1] = - Math.abs(nums[num-1]);
            }
        }

        for (int i = 0; i < n; i++) {
            if(nums[i] > 0){
                return i + 1;  //返回第一个大于0的下标 + 1
            }
        }

        return n + 1;
    }

    public static void main(String[] args) {

        L41FirstMissingPositive a = new L41FirstMissingPositive();

        int[] arr = new int[]{1,2,0};

        System.out.println(a.firstMissingPositive(arr));

    }
}
