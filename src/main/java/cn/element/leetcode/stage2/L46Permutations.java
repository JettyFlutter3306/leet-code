package cn.element.leetcode.stage2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 */
public class L46Permutations {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();

        dfs(nums,lists,stack);

        return lists;
    }

    /**
     * 深度优先遍历
     */
    public void dfs(int[] nums, List<List<Integer>> lists, Stack<Integer> stack){

        if(nums.length <= 0){
            lists.add(new ArrayList<>(stack));
        }else{
            for (int i = 0; i < nums.length; i++) {
                stack.push(nums[i]);  //将元素入栈

                int[] temp = new int[nums.length - 1];  //声明一个长度比nums数组少一的临时数组,用作递归参数

                System.arraycopy(nums,0,temp,0,i);  //拷贝nums数组中除了第i个元素之外的所有元素
                System.arraycopy(nums,i+1,temp,i,nums.length - i - 1);

                dfs(temp,lists,stack);  //递归调用

                stack.pop();  //状态重置
            }
        }
    }

    public static void main(String[] args) {

        L46Permutations a = new L46Permutations();

        int[] arr = {1};

        List<List<Integer>> lists = a.permute(arr);

        System.out.println(lists);
    }
}
