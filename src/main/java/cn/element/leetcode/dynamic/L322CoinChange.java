package cn.element.leetcode.dynamic;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 示例 4：
 * 输入：coins = [1], amount = 1
 * 输出：1
 *
 * 示例 5：
 * 输入：coins = [1], amount = 2
 * 输出：2
 */
public class L322CoinChange {

    /**
     * 设dp[i]表示组成金额i所需最少的硬币数量,假设在计算dp[i]之前,
     * 我们已经计算出dp[0] - dp[i - 1]的答案,则对应的状态转移方程则为:
     *      dp[i] = min(dp[i - cj]) + 1
     * 其中cj表示的是第j枚硬币的面值,即我们枚举最后一枚硬币面额是cj
     * 那么需要从i - cj这个金额状态dp[i - cj]转移过来
     * 再算上枚举的这枚硬币数量1的贡献,由于要硬币数量最少,
     * 所以dp[i]为前面转移过来的状态的最小值加上枚举的硬币数量1
     */
    public int coinChange(int[] coins, int amount) {
        int ans = amount + 1;
        int[] dp = new int[amount + 1];

        Arrays.fill(dp, ans);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        L322CoinChange a = new L322CoinChange();

        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println(a.coinChange(coins, amount));

    }
}
