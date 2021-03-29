package cn.element.leetcode.day01;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 *
 * 示例 1：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * 示例 3：
 *
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 */
public class L6ZigzagConversion {

    /**
     * 思路: 把每一行都想象成一个StringBuilder,然后把每个StringBuilder放入到一个数组中去
     * 定义一个index记录索引值,再定义一个direction记录递增或者递减方向,让index每次都 += direction
     * 周而复始,从0到numsRow再从numsRow到0
     * 最后把这些StringBuilder拼接到一个StringBuilder上面,调用toString()方法,返回最终结果
     */
    public static String convert(String s, int numRows) {

        //这里判断直接返回
        if(s == null || s.length() <= numRows || numRows <= 1){
            return s;
        }

        StringBuilder[] array = new StringBuilder[numRows];

        for (int i = 0; i < array.length; i++) {
            array[i] = new StringBuilder();
        }

        int direction = 1;
        int index = 0;

        for (char c : s.toCharArray()) {
            array[index].append(c);

            index += direction;

            if(index == 0 || index == numRows - 1){
                direction = -direction;
            }
        }

        StringBuilder result = new StringBuilder();

        for (StringBuilder stringBuilder : array) {
            result.append(stringBuilder);
        }

        return result.toString();
    }

    public static void main(String[] args) {

        String s = "PAYPALISHIRING";
        String str = convert(s, 4);
        System.out.println(str);
    }


}
