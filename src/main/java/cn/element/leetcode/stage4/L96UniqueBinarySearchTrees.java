package cn.element.leetcode.stage4;

/**
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：5
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 */
public class L96UniqueBinarySearchTrees {

    /**
     * 法一:  动态规划
     * 给定一个有序序列 1 ⋯ n，为了构建出一棵二叉搜索树，我们可以遍历每个数字 i，将该数字作为树根，
     * 将 1 ⋯ (i−1) 序列作为左子树，将 (i+1) ⋯ n 序列作为右子树。
     * 接着我们可以按照同样的方式递归构建左子树和右子树。
     * 给定序列 1 ⋯ n，我们选择数字 i 作为根，
     * 则根为 i 的所有二叉搜索树的集合是左子树集合和右子树集合的笛卡尔积，
     * 对于笛卡尔积中的每个元素，加上根节点之后形成完整的二叉搜索树
     */
    public int numTrees(int n) {

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

    /**
     * 法二:  使用公式,卡塔兰数
     */
    public int numTrees1(int n) {

        // 提示：我们在这里需要用 long 类型防止计算过程中的溢出
        long c = 1;

        for (int i = 0; i < n; ++i) {
            c = c * 2 * (2 * i + 1) / (i + 2);
        }

        return (int) c;
    }

    public static void main(String[] args) {

        L96UniqueBinarySearchTrees a = new L96UniqueBinarySearchTrees();

        System.out.println(a.numTrees1(3));
    }
}
