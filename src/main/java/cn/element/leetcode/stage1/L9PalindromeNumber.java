package cn.element.leetcode.stage1;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 *
 * 示例 1：
 *
 * 输入：x = 121
 * 输出：true
 * 示例2：
 *
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 *
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 * 示例 4：
 *
 * 输入：x = -101
 * 输出：false
 */
public class L9PalindromeNumber {

    /**
     * 从现在开始尽量不要使用字符串,尽量使用取模和除法位移的思想
     * 不要总是依赖字符串,String不是万能的
     */
    public boolean isPalindrome(int x) {

        if(x == 0){
            return true;
        }

        if(x < 0 || x % 10 == 0){
            return false;
        }

        int n = x % 10;//拿到个位

        int result = x;  //保存x原来的值

        while(x / 10 != 0){
            x /= 10;

            n = n * 10 + x % 10;  //乘10往左移动一位,然后加上x右移一位的个位
        }

        return result == n;
    }

    public static void main(String[] args) {

        L9PalindromeNumber a = new L9PalindromeNumber();

        System.out.println(a.isPalindrome(0));
    }
}
