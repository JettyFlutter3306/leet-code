package cn.element.leetcode.stage2;

import java.util.Arrays;

/**
 * 给你一个正整数n ，生成一个包含 1 到n²所有元素，且元素按顺时针顺序螺旋排列的n x n 正方形矩阵 matrix 。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 */
public class L59SpiralMatrixII {

    public int[][] generateMatrix(int n) {

        if(n <= 0){
            return null;
        }

        if(n == 1){
            return new int[][]{{1}};
        }

        int[][] matrix = new int[n][n];

        //定义矩阵四个角的坐标
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;

        int count = 1;

        while(left < right){
            for (int i = left; i < right; i++) {  //更新第一行
                matrix[top][i] = count++;
            }

            for (int i = top; i < bottom; i++) {  //更新最后一列
                matrix[i][right] = count++;
            }

            for (int i = right; i > left; i--) {  //更新最后一行
                matrix[bottom][i] = count++;
            }

            for (int i = bottom; i > top; i--) {  //更新第一列
                matrix[i][left] = count++;
            }

            //所有坐标往里靠拢 增1 或 减1
            left++;
            right--;
            top++;
            bottom--;

            if(left == right){
                matrix[n/2][n/2] = n * n;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {

        L59SpiralMatrixII a = new L59SpiralMatrixII();

        int[][] matrix = a.generateMatrix(4);

        for (int[] temp : matrix) {
            System.out.println(Arrays.toString(temp));
        }
    }

}
