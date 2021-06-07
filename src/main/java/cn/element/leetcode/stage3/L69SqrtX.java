package cn.element.leetcode.stage3;

/**
 * 实现int sqrt(int x)函数。
 * 计算并返回x的平方根，其中x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */
public class L69SqrtX {

    /**
     * 袖珍计算器算法
     * 「袖珍计算器算法」是一种用指数函数 exp 和对数函数 ln 代替平方根函数的方法。我们通过有限的可以使用的数学函数，
     * 得到我们想要计算的结果。
     */
    public int mySqrt(int x) {

        if(x == 0){
            return 0;
        }

        int ans = (int) Math.exp(0.5 * Math.log(x));

        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

    /**
     * 二分查找
     */
    public int mySqrt1(int x) {

        int l = 0, r = x, ans = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }

    /**
     * 牛顿迭代
     */
    public int mySqrt2(int x) {

        if (x == 0) {
            return 0;
        }

        double x0 = x;

        while (true) {
            double xi = 0.5 * (x0 + (double) x / x0);

            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }

            x0 = xi;
        }

        return (int) x0;
    }

    public static void main(String[] args) {

        L69SqrtX a = new L69SqrtX();

        int digit = 13515487;

        System.out.println(a.mySqrt(digit));

    }
}
