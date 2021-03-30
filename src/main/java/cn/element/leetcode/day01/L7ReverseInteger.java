package cn.element.leetcode.day01;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 *
 * 输入：x = 0
 * 输出：0
 */
public class L7ReverseInteger {

    public static int reverse(int x) {

        if(x == Integer.MIN_VALUE){
            return 0;
        }

        int neg = x < 0 ? -1 : 1;//判断是否是小于0的数

        x *= neg;//给x加上符号

        int result = 0;

        while(x > 0){
            int n = result;

            n *= 10;//每次循环让n扩大10倍相当于左移一位给个位数字留下空间,乘10总会有数据溢出的那一刻

            n += x % 10;//让n加上x的个位数字

            x /= 10;//十进制数缩小10倍相当于右移一位

            if(n / 10 != result){//判断是否数据溢出
                return 0;
            }

            result = n;
        }

        return result * neg;//返回时不要忘记乘上符号位
    }


    public static void main(String[] args) {

        System.out.println(reverse(-1234567899));

    }


}
