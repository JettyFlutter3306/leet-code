package cn.element.leetcode.interview;

import java.util.HashSet;
import java.util.Set;

public class Test02 {

    /**
     * 返回一个不重复的数字
     */
    public int distinct(int num) {
        int ans = 0;

        Set<Integer> set = new HashSet<>();

        while (num % 10 != 0) {
            int temp = num % 10;

            if (!set.contains(temp)) {
                ans = ans * 10 + temp;
            }

            set.add(temp);


            num /= 10;
        }

        return ans;
    }

    public int maxCapacity(int[] values, int k, int d) {
        
        return 0;
    }

    public static void main(String[] args) {
        Test02 a = new Test02();

        int num = 99558413;

        System.out.println(a.distinct(num));
    }
}
