package cn.element.leetcode.stage3;

/**
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class L70ClimbingStairs {

    /**
     * 不可以用递归,因为递归性能太差会超出时间限制
     * 时间复杂度 O(2^n)  很垃圾
     */
    public int climbStairs(int n) {

        if(n == 1){
            return 1;
        }else if(n == 2){
            return 2;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 可以考虑动态规划
     */
    public int climbStairs1(int n) {

        if(n == 1){
            return 1;
        }else if(n == 2){
            return 2;
        }

        int[] dp = new int[n];

        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n - 1];
    }

    public int climbStairs2(int n) {

        int[][] q = {{1, 1}, {1, 0}};

        int[][] res = pow(q, n);

        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {

        int[][] ret = {{1, 0}, {0, 1}};

        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {

        int[][] c = new int[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    /**
     * 直接用斐波那契数列通项公式
     */
    public int climbStairs3(int n) {

        double sqrt5 = Math.sqrt(5);

        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);

        return (int) Math.round(fibn / sqrt5);
    }

    public static void main(String[] args) {

        L70ClimbingStairs a = new L70ClimbingStairs();

        System.out.println(a.climbStairs1(5));
    }
}
