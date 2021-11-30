package cn.element.leetcode.dynamic;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数2、3 和5的正整数。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 */
public class L264UglyNumberII {

    /**
     * 法一: 考虑使用最小堆
     * 要得到从小到大的第n个丑数,可以使用最小堆实现
     * 初始堆为空,首先将最小的丑数1加入堆
     * 每次取出堆顶元素x,则x是堆中最小的丑数,由于2x,3x,5x也是丑数
     * 因此将2x,3x,5x加入堆
     * 上述做法会产生重复元素的情况,去重可以使用HashSet
     * 时间复杂度: O(n*log n)
     * 空间复杂度: O(n)
     */
    public int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};

        Set<Long> seen = new HashSet<>();

        PriorityQueue<Long> heap = new PriorityQueue<>();

        seen.add(1L);
        heap.offer(1L);

        int ugly = 0;

        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;

            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }

        return ugly;
    }

    /**
     * 法二: 考虑使用动态规划
     * 法一使用最小堆,会预先存储较多的丑数,导致空间复杂度,导致空间
     * 复杂度较高,维护最小堆的过程也导致时间复杂度较高,可以使用动态规划的方法进行优化
     * 定义数组dp,其中dp[i]表示第i个丑数,第n个丑数即为dp[n]
     * 由于最小的丑数是1,因此dp[i] = 1
     * 定义三个指针,p2,p3,p5,表示下一个丑数是当前指针指向的丑数
     * 乘以对应的质因数,初始时,三个指针的值都是1
     * 当2 <= i <= n时,dp[i] = min(dp[p2] * 2, dp[p3] * 3, dp[p5] * 5)
     * 然后分别比较dp[i]和dp[p2]*2, dp[p3]*3, dp[p5]*5是否相等
     * 如果相等则将对应的指针加1
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int nthUglyNumber1(int n) {
        int[] dp = new int[n + 1];

        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;

        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2;
            int num3 = dp[p3] * 3;
            int num5 = dp[p5] * 5;

            dp[i] = Math.min(Math.min(num2, num3), num5);

            if (dp[i] == num2) {
                p2++;
            }

            if (dp[i] == num3) {
                p3++;
            }

            if (dp[i] == num5) {
                p5++;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        L264UglyNumberII a = new L264UglyNumberII();

        int n = 12;

        System.out.println(a.nthUglyNumber1(n));
    }
}
