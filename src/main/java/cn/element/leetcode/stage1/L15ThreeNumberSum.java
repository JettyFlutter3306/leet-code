package cn.element.leetcode.stage1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 */
public class L15ThreeNumberSum {

    /**
     * 使用双指针法,然后进行枚举
     */
    public List<List<Integer>> threeSum(int[] nums) {

        int n = nums.length;

        Arrays.sort(nums);

        List<List<Integer>> lists = new ArrayList<>();

        for (int first = 0; first < n; first++) {  //枚举a
            if(first > 0 && nums[first] == nums[first-1]){  //需要和上一次枚举的数不一样
                continue;
            }

            int third = n - 1;  //c对应的指针初始指向数组的最右端

            int target = -nums[first];

            for (int second = first + 1; second < n; second++) {
                if(second > first + 1 && nums[second] == nums[second-1]){  //需要和上一次枚举的数不相同
                    continue;
                }

                while(second < third && nums[second] + nums[third] > target){  //需要保证b的指针在c指针的左侧
                    third--;
                }

                //如果指针重合,随着b后续的增加,就不会有满足a+b+c=0并且b<c的c了可以退出循环
                if(second == third){
                    break;
                }

                if(nums[second] + nums[third] == target){
                    List<Integer> list = new ArrayList<>();

                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);

                    lists.add(list);
                }

            }

        }

        return lists;
    }


    public static void main(String[] args) {

        int[] nums = {-1,0,1,2,-1,-4};

        L15ThreeNumberSum a = new L15ThreeNumberSum();

        System.out.println(a.threeSum(nums));
    }


}
