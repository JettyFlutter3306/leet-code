package cn.element.leetcode.stage1;

/**
 * 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数dividend除以除数divisor得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，
 * 例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 *
 * 示例1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 *
 * 示例2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 */
public class L29DivideTwoIntegers {

    public int divide(int dividend, int divisor) {

        if(dividend == 0){  //特殊情况处理
            return 0;
        }

        if(divisor == 1){
            return dividend;
        }

        if(divisor == -1){
            if(dividend == Integer.MIN_VALUE){
                return Integer.MAX_VALUE;
            }

            return -dividend;
        }

        //通过异或,判断结果是正还是负
        int symbol = 1;

        if((dividend ^ divisor) < 0){
            symbol = -1;
        }

        //将除数和被除数都改为负数,避免溢出
        if(dividend > 0){
            dividend = -dividend;
        }

        if(divisor > 0){
            divisor = -divisor;
        }

        //通过重复二进制处理,进行计算
        int result = 0;

        while(divisor >= dividend){
            boolean isRight = true;

            int subRight = 1;

            int tempDivisor = divisor;

            while(tempDivisor >= dividend){
                if(tempDivisor < (Integer.MIN_VALUE >> 1)){
                    isRight = false;

                    break;
                }

                tempDivisor <<= 1;
                subRight <<= 1;
            }

            if(isRight){
                tempDivisor >>= 1;
                subRight >>= 1;
            }

            dividend -= tempDivisor;

            result += subRight;
        }

        if(symbol == -1){
            return -result;
        }

        return result;
    }

    public static void main(String[] args) {

        L29DivideTwoIntegers a = new L29DivideTwoIntegers();

        System.out.println(a.divide(10,3));

//        System.out.println(-8 ^ 8);

    }
}
