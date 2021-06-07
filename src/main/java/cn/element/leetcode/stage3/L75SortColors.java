package cn.element.leetcode.stage3;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 *
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[0]
 *
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 */
public class L75SortColors {

    /**
     * 法一: 单指针
     */
    public void sortColors(int[] nums) {

        int n = nums.length;
        int ptr = 0;

        for (int i = 0; i < n; i++) {
            if(nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;

                ptr++;
            }
        }

        for (int i = ptr; i < n; i++) {
            if(nums[i] == 1){
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;

                ptr++;
            }
        }
    }

    /**
     * 法二: 双指针
     */
    public void sortColors1(int[] nums){

        int n = nums.length;
        int p0 = 0;
        int p1 = 0;

        for (int i = 0; i < n; i++) {
            if(nums[i] == 1){
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;

                p1++;
            }else if(nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;

                if(p0 < p1){
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }

                p0++;
                p1++;
            }
        }
    }

    /**
     * 法三: 双指针优化空间复杂度算法
     */
    public void sortColors2(int[] nums){

        int n = nums.length;
        int p0 = 0;
        int p2 = n - 1;

        for (int i = 0; i <= p2; i++) {
            while(i <= p2 && nums[i] == 2){
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;

                p2--;
            }

            if(nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;

                p0++;
            }
        }
    }

    public static void main(String[] args) {

        L75SortColors a = new L75SortColors();

        int[] arr = new int[]{2,0,2,1,1,0};

        a.sortColors1(arr);

        System.out.println(Arrays.toString(arr));
    }
}
