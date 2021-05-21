package cn.element.leetcode.stage2;

/**
 * 给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 *
 * 示例1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */
public class L55JumpGame {

    /**
     * 典型的贪心算法
     */
    public boolean canJump(int[] nums) {

        int reach = 0;  //可以跳跃到的位置

        for (int i = 0; i < nums.length; i++) {
            if(i > reach){  //假如当前索引已经大于可以跳跃到的位置,那么就直接返回false
                return false;
            }

            reach = Math.max(reach,nums[i] + i);  //更新当前的可以跳跃到的最远位置
        }

        return true;
    }

    public static void main(String[] args) {

        L55JumpGame a = new L55JumpGame();

        int[] arr = {2,3,1,1,4};

        System.out.println(a.canJump(arr));


    }
}
