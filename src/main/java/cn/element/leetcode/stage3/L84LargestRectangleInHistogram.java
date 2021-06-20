package cn.element.leetcode.stage3;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为[2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为10个单位。
 *
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class L84LargestRectangleInHistogram {

    /**
     * 法一: 暴力枚举法
     * 时间复杂度 O(n²)
     */
    public int largestRectangleArea(int[] heights) {

        int n = heights.length;  //获取长度

        int ans = 0;  //保存结果

        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];  //枚举高
            int left = mid;
            int right = mid;

            while(left - 1 >= 0 && heights[left - 1] >= height){
                left--;
            }

            while(right + 1 < n && heights[right + 1] >= height){
                right++;
            }

            ans = Math.max(ans,(right - left + 1) * height);  //更新一手结果
        }

        return ans;
    }

    /**
     * 法二: 单调栈
     */
    public int largestRectangleArea1(int[] heights) {

        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while(!stack.empty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }

            left[i] = stack.empty() ? -1 : stack.peek();

            stack.push(i);
        }

        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while(!stack.empty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }

            right[i] = stack.empty() ? n : stack.peek();

            stack.push(i);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans,(right[i] - left[i] - 1) * heights[i]);
        }

        return ans;
    }

    public static void main(String[] args) {

        L84LargestRectangleInHistogram a = new L84LargestRectangleInHistogram();

        int[] heights = new int[]{2,1,5,6,2,3};

        System.out.println(a.largestRectangleArea1(heights));
    }
}
