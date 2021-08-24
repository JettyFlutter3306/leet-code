package cn.element.leetcode.stage5;

/**
 * 给你一个整数数组nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 * 示例 1：
 * 输入：nums = [2,2,3,2]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 */
public class L137SingleNumberII {

    /**
     * 依次确定每一个二进制位
     * 由于数组中的元素都在 int 范围内,因此我们可以一次计算答案的每一个二进制位是 0 还是 1
     * 具体地,考虑答案第 i 个二进制位(i 从 0 开始编号),他可能为0或1,对于数组中非答案的元素,
     * 每一个元素都出现了 3 次,对应着第 i 个二进制位的 3 个 0 或 3 个 1,无论是哪一种情况,它们的和
     * 都是 3 的倍数
     * 因此: 答案的第 i 个二进制位就是数组中所有的元素的第 i 个二进制位和除以 3 的余数
     */
    public int singleNumber(int[] nums) {

        int ans = 0;

        for (int i = 0; i < 32; i++) {
            int total = 0;

            for (int num : nums) {
                total += ((num >> i) & 1);
            }

            if(total % 3 != 0){
                ans |= (1 << i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        L137SingleNumberII a = new L137SingleNumberII();

        int[] nums = {0,1,0,1,0,1,99};

        System.out.println(a.singleNumber(nums));
    }
}
