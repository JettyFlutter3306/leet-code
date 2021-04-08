package cn.element.leetcode.stage1;

import org.omg.CORBA.INTERNAL;

import java.util.Arrays;

/**
 * 给定一个包括n 个整数的数组nums和 一个目标值target。
 * 找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 */
public class L16ThreeSumClosest {

    //还是尝试使用啥双指针法
    public int threeSumClosest(int[] nums, int target) {

        int len = nums.length;

        Arrays.sort(nums);

        int best = 10000000;

        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1,k = len - 1;  //使用双指针枚举b和c

            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];

                if(sum == target){  //如果直接等于target那么返回sum
                    return sum;
                }

                if(Math.abs(sum - target) < Math.abs(best - target)){  //根据差值的绝对值来更新答案
                    best = sum;
                }

                if(sum > target){
                    int k0 = k - 1; //如果和大于target,移动c对应的指针

                    while(j < k0 && nums[k0] == nums[k]){  //移动到下一个不相等的元素
                        k0--;
                    }

                    k = k0;
                }else{  //如果和小于target,移动b对应的指针
                    int j0 = j + 1;

                    while(j0 < k && nums[j0] == nums[j]){ //移动到下一个不相等的元素
                        j0++;
                    }

                    j = j0;
                }
            }
        }

        return best;
    }

    public static void main(String[] args) {

        int[] arr = {-1,2,1,-4};

        L16ThreeSumClosest a = new L16ThreeSumClosest();

        System.out.println(a.threeSumClosest(arr, 1));

    }

}
