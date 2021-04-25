package cn.element.leetcode.stage2;

import java.util.Arrays;

/**
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 *
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 *
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 */
public class L31NextPermutation {

    /**
     * 注意到下一个排列总是要比当前的排列要大,除非已经是最大的排列
     * 在数组右边递增数列找到一个较大的数,然后找到一个比较大数大一点的数
     */
    public void nextPermutation(int[] nums) {

        int i = nums.length - 2;

        while(i >= 0 && nums[i] >= nums[i+1]){
            i--;
        }

        if(i >= 0){
            int j = nums.length - 1;

            while(j >= 0 && nums[i] >= nums[j]){
                j--;
            }

            swap(nums,i,j);
        }

        reverse(nums,i + 1);
    }

    /**
     * 换值
     */
    public void swap(int[] nums,int i,int j){

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 从指定位置开始反转数组
     */
    public void reverse(int[] nums,int start){

        //双指针法
        int left = start;
        int right = nums.length - 1;

        while(left < right){
            swap(nums,left,right);

            left++;
            right--;
        }
    }

    public static void main(String[] args) {

        int[] arr = {1,1};

        L31NextPermutation a = new L31NextPermutation();

        a.nextPermutation(arr);

        System.out.println(Arrays.toString(arr));
    }
}
