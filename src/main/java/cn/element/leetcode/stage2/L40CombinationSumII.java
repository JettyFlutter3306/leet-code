package cn.element.leetcode.stage2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 *
 * candidates中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例1:
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例2:
 * 输入: candidates =[2,5,2,1,2], target =5,
 * 所求解集为:
 * [
 *  [1,2,2],
 *  [5]
 * ]
 */
public class L40CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> lists = new ArrayList<>();

        if(candidates.length == 0){
            return lists;
        }

        Arrays.sort(candidates);  //关键步骤

        Stack<Integer> stack = new Stack<>();  //创建一个栈用于回溯

        dfs(candidates,target,0,stack,lists);  //递归 + 回溯

        return lists;
    }

    /**
     * 考虑使用递归回溯方式,
     * 必须理解74行代码去重的手法,因为事先排序好的,所以可以这么搞
     * @param candidates            候选数组
     * @param target                目标值
     * @param begin                 开始的索引
     * @param stack                 栈
     * @param lists                 结果收集列表
     */
    public void dfs(int[] candidates, int target, int begin, Stack<Integer> stack,List<List<Integer>> lists){

        if(target == 0){
            lists.add(new ArrayList<>(stack));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if(target < candidates[i]){  //大剪枝,减去candidates[i]<0的肯定不符合条件
                break;
            }

            if(i > begin && candidates[i] == candidates[i-1]){  //小剪枝,同一层相同数值的结点,从第2个开始,候选数开始减少,结果就一定发生重复,这行代码十分重要
                continue;
            }

            stack.push(candidates[i]);

//            System.out.println("递归之前 => " + stack + "，剩余 = " + (target - candidates[i]));

            dfs(candidates,target-candidates[i],i+1,stack,lists);  //注意应该传递 i+1 而不是 begin+1,这边犯傻了

            stack.pop();

//            System.out.println("递归之后 => " + stack + "，剩余 = " + (target - candidates[i]));
        }
    }

    public static void main(String[] args) {

        L40CombinationSumII a = new L40CombinationSumII();

        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};

        int target = 8;

        List<List<Integer>> lists = a.combinationSum2(candidates, target);

        lists.forEach(System.out::println);

    }
}
