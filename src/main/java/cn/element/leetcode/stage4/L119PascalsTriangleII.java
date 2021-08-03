package cn.element.leetcode.stage4;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 示例 1:
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 *
 * 示例 2:
 * 输入: rowIndex = 0
 * 输出: [1]
 *
 * 示例 3:
 * 输入: rowIndex = 1
 * 输出: [1,1]
 */
public class L119PascalsTriangleII {

    /**
     * 利用组合数公式,可以得出同一行的相邻组合数的关系
     */
    public List<Integer> getRow(int rowIndex) {

        List<Integer> list = new ArrayList<>();

        list.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            list.add((int)((long) list.get(i - 1) * (rowIndex - i + 1) / i));
        }

        return list;
    }

    public static void main(String[] args) {

        L119PascalsTriangleII a = new L119PascalsTriangleII();

        List<Integer> list = a.getRow(5);

        System.out.println(list);
    }
}
