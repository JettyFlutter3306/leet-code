package cn.element.leetcode.stage1;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L1TwoSum {

    /**
     * 法一. 穷举法
     */
    public static int[] twoSum1(int[] nums,int target){

        int[] arr = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target){
                    arr[0] = i;
                    arr[1] = j;

                    return arr;
                }
            }
        }

        return arr;
    }


    public static void main(String[] args) {

        int[] nums = {2,7,11,15};
        int target = 9;

        int[] arr = twoSum1(nums, target);

        System.out.println(Arrays.toString(arr));


    }
}
