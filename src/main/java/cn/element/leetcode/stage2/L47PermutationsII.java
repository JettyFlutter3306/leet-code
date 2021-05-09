package cn.element.leetcode.stage2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class L47PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();

        if(nums == null || nums.length == 0){
            return lists;
        }

        Stack<Integer> stack = new Stack<>();

        Arrays.sort(nums);

        dfs(nums,lists,stack);

        return lists;
    }

    public void dfs(int[] nums, List<List<Integer>> lists, Stack<Integer> stack){

        if(nums.length <= 0){
            lists.add(new ArrayList<>(stack));
        }else{
            for (int i = 0; i < nums.length; i++) {
                if(i > 0 && nums[i] == nums[i-1]){  //从这里开始剪枝,若有重复的那么就结束本次循环
                    continue;
                }

                stack.push(nums[i]);

                int[] temp = new int[nums.length - 1];

                System.arraycopy(nums,0,temp,0,i);  //拷贝数组其他元素
                System.arraycopy(nums,i+1,temp,i,nums.length - 1 - i);

                dfs(temp,lists,stack);  //递归调用

                stack.pop();  //回溯,状态重置
            }
        }
    }



    public static void main(String[] args) {

        L47PermutationsII a = new L47PermutationsII();

        int[] arr = {1,1,2};

        List<List<Integer>> lists = a.permuteUnique(arr);

        lists.forEach(System.out::println);

    }
}
