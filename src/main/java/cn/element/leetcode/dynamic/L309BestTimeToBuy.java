package cn.element.leetcode.dynamic;

/**
 * 给定一个整数数组，其中第i个元素代表了第i天的股票价格
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class L309BestTimeToBuy {

    /**
     * 一种常用的方法就是将[买入]和[卖出]分开进行考虑
     * [买入]为负收益,而[卖出]是正收益,在出入股市时
     * 只有[买入]的权利,只能获得负收益,而当[买入]之后
     * 就有了[卖出]的权利,可以获得正收益,显然我们需要尽可能地
     * 降低负收益而提高正收益,因此我们的目标总是将收益最大化
     * 因此,我们可以使用动态规划的方法,维护在股市中每一天结束后
     * 可以获得的[累计最大收益],并以此进行状态转移,得到最终的收益
     *
     * 设dp[i]表示第i天结束之后[累计最大收益],根据题目描述,由于
     * 我们最多只能同时买入(持有)一支股票,并且卖出股票后有冷冻期的限制
     * 因此会有三种不同的状态:
     * 1.我们目前持有一支股票，对应的「累计最大收益」记为 f[i][0]
     * 2.我们目前不持有任何股票，并且处于冷冻期中，对应的「累计最大收益」记为 f[i][1]
     * 3.我们目前不持有任何股票，并且不处于冷冻期中，对应的「累计最大收益」记为 f[i][2]
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;

        //dp[i][0]手上持有股票的最大收益值
        //dp[i][1]手上不持有股票,并且处于冷冻期的累计最大收益值
        //dp[i][2]手上不持有股票,并且不在冷冻期中的累计最大收益
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }

        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }

    public static void main(String[] args) {
        L309BestTimeToBuy a = new L309BestTimeToBuy();

        int[] prices = {1,2,3,0,2};

        System.out.println(a.maxProfit(prices));
    }
}
