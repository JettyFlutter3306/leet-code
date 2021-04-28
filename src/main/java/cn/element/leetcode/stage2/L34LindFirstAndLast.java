package cn.element.leetcode.stage2;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class L34LindFirstAndLast {

    /**
     * 遍历一次数组
     */
    public int[] searchRange(int[] nums, int target) {

        int[] result = new int[]{-1,-1};

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == target && result[0] == -1){  //保留第一次出现的结果
                result[0] = i;

                continue;
            }

            if(nums[i] == target){  //第一次之后的结果
                result[1] = i;
            }
        }

        if(result[0] != -1 && result[1] == -1){  //假如这个元素只在数组里面出现了一次,那么就要变更result[1]的结果和result[0]一样
            result[1] = result[0];
        }

        return result;
    }

    /**
     * 二分查找法
     * 时间复杂度 O(log n)
     */
    public int[] searchRange1(int[] nums,int target){

        int[] result = new int[]{-1,-1};

        int left = binarySearch(nums,target,true);

        int right = binarySearch(nums,target,false) - 1;

        if(left <= right && right < nums.length && nums[left] == target && nums[right] == target){
             result[0] = left;
             result[1] = right;
        }

        return result;
    }

    //二分查找辅助函数
    public int binarySearch(int[] nums,int target,boolean lower){

        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;

        while(left <= right){
            int mid = (left + right) / 2;  //除以2,取中间

            if(nums[mid] > target || (lower && nums[mid] >= target)){
                right = mid - 1;

                ans = mid;
            }else{
                left = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        L34LindFirstAndLast a = new L34LindFirstAndLast();

        int[] arr = {5,7,7,8,8,8,8,8,8,8,10};

        int[] result = a.searchRange1(arr, 8);

        System.out.println(Arrays.toString(result));

        System.out.println(a.binarySearch(arr,8,false) - 1);
    }
}
