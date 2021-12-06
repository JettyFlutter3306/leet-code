package cn.element.leetcode.dynamic;

import java.util.Arrays;

/**
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 *
 * 示例 1：
 * 输入：n = 12, primes = [2,7,13,19]
 * 输出：32
 * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 *
 * 示例 2：
 * 输入：n = 1, primes = [2,3,5]
 * 输出：1
 * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
 */
public class L313SuperUglyNumber {

    /**
     * 考虑使用动态规划:
     *  设dp[i]表示第i个超级丑数,由于最小的超级丑数为1,因此dp[1] = 1
     *  创建与数组primes相同长度的数组pointers,表示下一个超级丑数是当前
     *  指针指向的超级丑数乘以对应的质因数,初始化时,数组pointers的元素值都是1
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n + 1];
        int m = primes.length;
        int[] pointers = new int[m];
        int[] nums = new int[m];

        Arrays.fill(nums, 1);

        for (int i = 1; i <= n; i++) {
            int minNum = Arrays.stream(nums).min().getAsInt();

            dp[i] = minNum;

            for (int j = 0; j < m; j++) {
                if (nums[j] == minNum) {
                    pointers[j]++;
                    nums[j] = dp[pointers[j]] * primes[j];
                }
            }

        }

        return dp[n];
    }

    public static void main(String[] args) {
        L313SuperUglyNumber a = new L313SuperUglyNumber();

        int n = 12;
        int[] primes = {2,7,13,19};

        System.out.println(a.nthSuperUglyNumber(n, primes));
    }
}
