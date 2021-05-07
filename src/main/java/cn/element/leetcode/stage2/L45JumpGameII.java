package cn.element.leetcode.stage2;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 *
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *       从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
 *
 * 示例 2:
 * 输入: [2,3,0,1,4]
 * 输出: 2
 */
public class L45JumpGameII {

    /**
     * 可以考虑使用贪心算法,找到距离最后一个位置最远的那个位置,也就是对应下标最小的位置
     */
    public int jump(int[] nums) {

        int position = nums.length - 1;

        int steps = 0;

        while(position > 0){
            for (int i = 0; i < position; i++) {
                if(i + nums[i] >= position){  //假如当前的索引加上对应的值大于或等于position那么就position赋值为i然后退出循环
                    position = i;

                    steps++;

                    break;
                }
            }
        }

        return steps;
    }

    public static void main(String[] args) {

        L45JumpGameII a = new L45JumpGameII();

        int[] arr = {2,3,1,1,4};

        System.out.println(a.jump(arr));
    }
}
