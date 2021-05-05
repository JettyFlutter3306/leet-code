package cn.element.leetcode.stage2;

/**
 * 给定两个以字符串形式表示的非负整数num1和num2，返回num1和num2的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 */
public class L43MultiplyStrings {

    public String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        String ans = "0";

        int m = num1.length(), n = num2.length();

        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();

            int add = 0;

            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }

            int y = num2.charAt(i) - '0';

            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;

                curr.append(product % 10);

                add = product / 10;
            }

            if (add != 0) {
                curr.append(add % 10);
            }

            ans = addStrings(ans, curr.reverse().toString());
        }

        return ans;
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;

        StringBuffer ans = new StringBuffer();

        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;

            int result = x + y + add;

            ans.append(result % 10);

            add = result / 10;

            i--;
            j--;
        }

        ans.reverse();

        return ans.toString();
    }

    public static void main(String[] args) {

        L43MultiplyStrings a = new L43MultiplyStrings();

        String str1 = "123";
        String str2 = "321";

        System.out.println(a.multiply(str1,str2));

    }
}
