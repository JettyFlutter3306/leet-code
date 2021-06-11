package cn.element.leetcode.stage3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你一个整数数组nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class L78Subsets {

    List<Integer> t = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    /**
     * 字典排序
     */
    public List<List<Integer>> subsets(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < (1 << n); i++) {  //总共有2 ^ n个子集
            t.clear();

            for (int j = 0; j < n; j++) {
                if((i & (1 << j)) != 0){
                    t.add(nums[j]);
                }
            }

            ans.add(new ArrayList<>(t));
        }

        return ans;
    }

    /**
     * 回溯递归
     */
    public List<List<Integer>> subsets1(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();

        List<Integer> path = new ArrayList<>();

        dfs(nums, 0, nums.length, path, res);

        return res;
    }

    private void dfs(int[] nums, int begin, int n, List<Integer> path, List<List<Integer>> res) {

        res.add(new ArrayList<>(path));

        for (int i = begin; i < n; i++) {
            path.add(nums[i]);

            dfs(nums, i + 1, n, path, res);

            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {

        L78Subsets a = new L78Subsets();

        int[] nums = {1,2,3};

        List<List<Integer>> lists = a.subsets1(nums);

        lists.forEach(System.out::println);
    }
}
