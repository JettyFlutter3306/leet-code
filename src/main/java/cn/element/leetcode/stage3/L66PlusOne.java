package cn.element.leetcode.stage3;

import java.util.Arrays;

/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 *
 * 示例2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 *
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 */
public class L66PlusOne {

    public int[] plusOne(int[] digits) {

        int n = digits.length;

        boolean flag = true;  //表示是否有进位,初始值为true

        for (int i = n - 1; i >= 0; i--) {
            if(flag){
                int temp = digits[i] + 1;

                if(temp == 10){
                    digits[i] = 0;

                    if(i == 0){
                        int[] arr = new int[n + 1];

                        arr[0] = 1;

                        System.arraycopy(digits,0,arr,1,n);

                        return arr;
                    }
                }else{
                    digits[i] += 1;

                    flag = false;
                }
            }
        }

        return digits;
    }

    public static void main(String[] args) {

        L66PlusOne a = new L66PlusOne();

        int[] digits = {9,9,9,9};

        System.out.println(Arrays.toString(a.plusOne(digits)));
    }
}
