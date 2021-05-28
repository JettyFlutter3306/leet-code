package cn.element.leetcode.stage2;

import java.util.Arrays;

/**
 * 给出集合[1,2,3,...,n]，其所有元素共有n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定n 和k，返回第k个排列。
 *
 * 示例 1：
 * 输入：n = 3, k = 3
 * 输出："213"
 *
 * 示例 2：
 * 输入：n = 4, k = 9
 * 输出："2314"
 *
 * 示例 3：
 * 输入：n = 3, k = 1
 * 输出："123"
 */
public class L60PermutationSequence {

    /**
     * 记录数字是否使用过
     */
    private boolean[] used;

    /**
     * 阶乘数组
     */
    private int[] factorial;

    private int n;
    private int k;

    public String getPermutation(int n, int k) {

        this.n = n;
        this.k = k;

        calculateFactorial(n);

        // 查找全排列需要的布尔数组
        used = new boolean[n + 1];

        Arrays.fill(used, false);

        StringBuilder path = new StringBuilder();

        dfs(0, path);

        return path.toString();
    }


    /**
     * @param index 在这一步之前已经选择了几个数字，其值恰好等于这一步需要确定的下标位置
     */
    private void dfs(int index, StringBuilder path) {

        if (index == this.n) {
            return;
        }

        int cnt = factorial[this.n - 1 - index];  // 计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1

        for (int i = 1; i <= this.n; i++) {
            if (used[i]) {
                continue;
            }

            if (cnt < this.k) {
                this.k -= cnt;

                continue;
            }

            path.append(i);

            used[i] = true;

            dfs(index + 1, path);

            // 注意 1：不可以回溯（重置变量），算法设计是「一下子来到叶子结点」，没有回头的过程
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    /**
     * 计算阶乘数组
     */
    private void calculateFactorial(int n) {

        factorial = new int[n + 1];

        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

    public static void main(String[] args) {

        L60PermutationSequence a = new L60PermutationSequence();

        System.out.println(a.getPermutation(4, 9));

    }
}
