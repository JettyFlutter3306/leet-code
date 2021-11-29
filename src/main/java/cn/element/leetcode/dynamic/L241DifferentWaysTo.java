package cn.element.leetcode.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。
 * 你需要给出所有可能的组合的结果。有效的运算符号包含 +,-以及*。
 *
 * 示例1:
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 *
 * 示例2:
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */
public class L241DifferentWaysTo {

    /**
     * 法一: 使用递归
     * 以 2 * 3 - 4 * 5 为例
     * 分割成 2 和 3 - 4 * 5 两部分
     * 那么一边的结果就是[2] 另一边 3 - 4 * 5 再次分割 的结果就是[-5, -17]
     * 因为中间使用 * 连接,所以最终结果就是[-10, -34]
     */
    public List<Integer> diffWaysToCompute(String expression) {
        if (expression.length() == 0) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        int num = 0;

        //考虑全是数字的情况
        int index = 0;

        while (index < expression.length() && !isOperation(expression.charAt(index))) {
            num = num * 10 + expression.charAt(index) - '0';
            index++;
        }

        //将全数字的情况直接返回
        if (index == expression.length()) {
            result.add(num);
            return result;
        }

        for (int i = 0; i < expression.length(); i++) {
            //通过运算符将字符串分割成两个部分
            if (isOperation(expression.charAt(i))) {
                List<Integer> result1 = diffWaysToCompute(expression.substring(0, i));
                List<Integer> result2 = diffWaysToCompute(expression.substring(i + 1));

                //将两个结果依次运算
                for (Integer val1 : result1) {
                    for (Integer val2 : result2) {
                        char op = expression.charAt(i);
                        result.add(calculate(val1, op, val2));
                    }
                }
            }
        }

        return result;
    }

    /**
     * 考虑使用动态规划法
     * 最巧妙的地方就是做一个预处理，把每个数字提前转为 int 然后存起来，同时把运算符也都存起来。
     * 这样的话我们就有了两个 list，一个保存了所有数字，一个保存了所有运算符。
     *
     * 2 * 3 - 4 * 5
     * 存起来的数字是 numList = [2 3 4 5]，
     * 存起来的运算符是 opList = [*, -, *]。
     *
     * dp[i][j] 也比较好定义了，含义是第 i 到第 j 个数字（从 0 开始计数）范围内的表达式的所有解。
     *
     * 举个例子，2 * 3 - 4 * 5
     * dp[1][3] 就代表第一个数字 3 到第三个数字 5 范围内的表达式 3 - 4 * 5 的所有解。
     * 初始条件的话，也很简单了，就是范围内只有一个数字。
     *
     * 2 * 3 - 4 * 5
     * dp[0][0] = [2]，dp[1][1] = [3]，dp[2][2] = [4]，dp[3][3] = [5]
     *
     * 有了一个数字的所有解，然后两个数字的所有解就可以求出来。
     *
     * 有了两个数字的所有解，然后三个数字的所有解就和解法一求法一样。
     *
     * 把三个数字分成两部分，将两部分的解两两组合起来即可。
     *
     * 两部分之间的运算符的话，因为表达式是一个数字一个运算符，所以运算符的下标就是左部分最后一个数字的下标。
     * 看下边的例子。
     *
     *
     * 2 * 3 - 4 * 5
     * 存起来的数字是 numList = [2 3 4 5]，
     * 存起来的运算符是 opList = [*, -, *]。
     *
     * 假设我们求 dp[1][3]
     * 也就是计算 3 - 4 * 5 的解
     * 分成 3 和 4 * 5 两部分，3 对应的下标是 1 ，对应的运算符就是 opList[1] = '-' 。
     * 也就是计算 3 - 20 = -17
     *
     * 分成 3 - 4 和 5 两部分，4 的下标是 2 ，对应的运算符就是 opList[2] = '*'。
     * 也就是计算 -1 * 5 = -5
     *
     * 所以 dp[1][3] = [-17 -5]
     */
    public List<Integer> diffWaysToCompute1(String expression) {
        List<Integer> numList = new ArrayList<>();
        List<Character> opList = new ArrayList<>();

        char[] array = expression.toCharArray();
        int num = 0;

        for (char c : array) {
            if (isOperation(c)) {
                numList.add(num);
                num = 0;

                opList.add(c);
                continue;
            }

            num = num * 10 + c - '0';
        }

        numList.add(num);
        int total = numList.size(); // 数字的个数

        // 一个数字
        ArrayList<Integer>[][] dp = (ArrayList<Integer>[][]) new ArrayList[total][total];

        for (int i = 0; i < total; i++) {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(numList.get(i));
            dp[i][i] = result;
        }

        // 2 个数字到 total 个数字
        for (int n = 2; n <= total; n++) {
            // 开始下标
            for (int i = 0; i < total; i++) {
                // 结束下标
                int j = i + n - 1;

                if (j >= total) {
                    break;
                }

                ArrayList<Integer> result = new ArrayList<>();

                // 分成 i ~ s 和 s+1 ~ j 两部分
                for (int s = i; s < j; s++) {
                    ArrayList<Integer> result1 = dp[i][s];
                    ArrayList<Integer> result2 = dp[s + 1][j];

                    for (Integer val1 : result1) {
                        for (Integer val2 : result2) {
                            // 第 s 个数字下标对应是第 s 个运算符
                            char op = opList.get(s);
                            result.add(calculate(val1, op, val2));
                        }
                    }
                }

                dp[i][j] = result;
            }
        }

        return dp[0][total - 1];
    }

    private int calculate(int num1, char c, int num2) {
        switch (c) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }

        return -1;
    }

    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }

    public static void main(String[] args) {
        L241DifferentWaysTo a = new L241DifferentWaysTo();

        String expression = "2*3-4*5";

        System.out.println(a.diffWaysToCompute1(expression));

    }
}
