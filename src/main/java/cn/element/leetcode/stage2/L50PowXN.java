package cn.element.leetcode.stage2;

/**
 * 实现pow(x, n)，即计算 x 的 n 次幂函数（即，x^n）。
 *
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 *
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2^(-2) = 1/2^2 = 1/4 = 0.25
 */
public class L50PowXN {

    public double myPow(double x, int n) {

        if(x == 0 && n == 0){
            throw new ArithmeticException();
        }

        if(x == 1.0){
            return 1.0;
        }else if(x == 0.0){
            return 0.0;
        }else if(x == -1.0){
            if(n % 2 == 0){
                return 1.0;
            }else if(n % 2 == 1){
                return -1.0;
            }
        }

        long temp = n;

        if(temp < 0){
            x = 1/x;

            temp = -temp;
        }

        double result = 1.0;

        for (long i = 0; i < temp; i++) {
            result *= x;
        }

        return result;
    }

    /**
     * 快速幂算法
     */
    public double myPow1(double x, int n) {

        long temp = n;

        return temp >= 0 ? quickMul(x, temp) : 1.0 / quickMul(x, -temp);
    }

    public double quickMul(double x, long temp) {

        if (temp == 0) {
            return 1.0;
        }

        double y = quickMul(x, temp / 2);

        return temp % 2 == 0 ? y * y : y * y * x;
    }

    public static void main(String[] args) {

        L50PowXN a = new L50PowXN();

        double x = 2.10000;
        int n = 3;

        System.out.println(a.myPow(x, n));
    }

}
