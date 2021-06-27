package cn.element.leetcode.stage3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class L90SubsetsII {

    /**
     * 首先考虑一下递归回溯解法
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();

        Arrays.sort(nums);

        List<Integer> list = new ArrayList<>();

        backTrack(nums, 0, list, lists);

        return lists;
    }

    private void backTrack(int[] nums, int start, List<Integer> list, List<List<Integer>> lists) {

        lists.add(new ArrayList<>(list));

        for (int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i - 1]){  //和上一个数字相等那么就跳过
                continue;
            }

            list.add(nums[i]);

            backTrack(nums, i + 1 , list, lists);

            list.remove(list.size() - 1);
        }
    }

    List<Integer> t = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    /**
     * 考虑迭代法去重
     */
    public List<List<Integer>> subsetsWithDup1(int[] nums) {

        Arrays.sort(nums);

        int n = nums.length;

        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();

            boolean flag = true;

            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        flag = false;

                        break;
                    }

                    t.add(nums[i]);
                }
            }

            if (flag) {
                ans.add(new ArrayList<>(t));
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        L90SubsetsII a = new L90SubsetsII();

        int[] nums = {4,4,4,1,4};

        List<List<Integer>> lists = a.subsetsWithDup(nums);

        lists.forEach(System.out::println);

    }
}
