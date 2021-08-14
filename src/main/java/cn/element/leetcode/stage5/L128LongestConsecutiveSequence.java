package cn.element.leetcode.stage5;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为O(n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
public class L128LongestConsecutiveSequence {

    /**
     * 考虑使用哈希表
     */
    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<>();  //使用HashSet填充数组的元素,提高查询效率

        for (int i : nums) {  //填充元素
            set.add(i);
        }

        int ans = 0;  //返回的结果

        for (Integer i : set) {
            if(!set.contains(i - 1)){
                int currentNum = i;
                int currentStreak = 1;

                while(set.contains(currentNum + 1)){
                    currentNum += 1;
                    currentStreak += 1;
                }

                ans = Math.max(ans, currentStreak);
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        L128LongestConsecutiveSequence a = new L128LongestConsecutiveSequence();

        int[] nums = {100,4,200,1,3,2};

        System.out.println(a.longestConsecutive(nums));
    }
}
