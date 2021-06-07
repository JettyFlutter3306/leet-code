package cn.element.leetcode.stage3;


/**
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字1和0。
 *
 * 示例1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class L67AddBinary {

    public String addBinary(String a, String b) {

        int m = a.length();
        int n = b.length();

        int temp = Math.max(m,n);  //取最小的长度
        int carry = 0;  //定义进位

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < temp; i++) {
            carry += i < m ? (a.charAt(m - 1 - i) - '0') : 0;
            carry += i < n ? (b.charAt(n - 1 - i) - '0') : 0;

            sb.append((char)(carry % 2 + '0'));

            carry /= 2;
        }

        if(carry > 0){
            sb.append('1');
        }

        sb.reverse();

        return sb.toString();
    }

    public static void main(String[] args) {

        L67AddBinary a = new L67AddBinary();

        String s1 = "101011";
        String s2 =   "1011";

        System.out.println(a.addBinary(s1, s2));

    }
}
