package cn.element.leetcode.interview;

public class Test01 {

    /**
     * 给定一个字符类型的数组chas[] chas右边半区是全空字符
     * 左半区不含有空字符 现在想办法将左半区的空格字符串
     * 换成 %20  假设右半区足够大 可以满足需要的空间
     */
    public String changespaceto20 (String str) {
        int len = str.length();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch != ' ') {
                sb.append(ch);
            } else {
                sb.append("%20");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Test01 a = new Test01();

        String s = "a   b      c";

        System.out.println(a.changespaceto20(s));
    }
}
