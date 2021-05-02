package cn.element.leetcode.stage2;

import java.util.*;

/**
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 *
 * candidates中的数字可以无限制重复被选取。
 *
 * 说明：
 * 所有数字（包括target）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *  [2,2,2,2],
 *  [2,3,3],
 *  [3,5]
 * ]
 */
public class L39CombinationSum {

    /**
     * 这显然是背包问题
     * @param candidates        无重复元素数组
     * @param target            目标值
     * @return                  List
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> lists = new ArrayList<>();

        int len = candidates.length;

        if(len == 0){
            return lists;
        }

        Arrays.sort(candidates);  //排序是剪枝的前提

        Stack<Integer> stack = new Stack<>();

        dfs(candidates,0,len,target,stack,lists);

        return lists;
    }

    /**
     * 剪枝提速
     * @param candidates        候选数组
     * @param begin             搜索起点
     * @param len               冗余变量,是candidates里的属性,可以传
     * @param target            每减去一个元素,目标值减小
     * @param stack              从根结点到叶子结点的路径是一个栈
     * @param res               结果集列表
     */
    public void dfs(int[] candidates, int begin, int len, int target, Stack<Integer> stack,List<List<Integer>> res){

        if(target == 0){
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = begin; i < len; i++) {  //重点是理解这里从begin开始搜索的语义
            if(target - candidates[i] < 0){  //target为负数和为0的时候不在产生新的孩子结点,从这里开始剪枝
                break;
            }

            stack.push(candidates[i]);

            dfs(candidates,i,len,target-candidates[i],stack,res);  //注意,由于每一个元素可以重复使用,下一轮搜索的起点依然是i

            stack.pop();  //状态重置
        }
    }

    public static void main(String[] args) {

        L39CombinationSum a = new L39CombinationSum();

        int[] candidates = new int[]{2,3,6,7};

        int target = 7;

        List<List<Integer>> lists = a.combinationSum(candidates, target);

        lists.forEach(System.out::println);
    }
}
