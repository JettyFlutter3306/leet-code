package cn.element.leetcode.stage2;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class L54SpiralMatrix {

    /**
     * 按层遍历
     */
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> order = new ArrayList<>();

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return order;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;

        //声明二阶矩阵的行列坐标
        int left = 0;
        int right = columns - 1;
        int top = 0;
        int bottom = rows - 1;

        while(left <= right && top <= bottom){
            for (int column = left; column < right; column++) {  //添加第一行
                order.add(matrix[top][column]);
            }

            for (int row = top; row <= bottom; row++) {  //添加最后一列
                order.add(matrix[row][right]);
            }

            if(left < right && top < bottom){
                for (int column = right - 1; column > left; column--) {  //添加最后一行
                    order.add(matrix[bottom][column]);
                }

                for (int row = bottom; row > top; row--) {  //添加第一列
                    order.add(matrix[row][left]);
                }
            }

            //往里面再进入一层
            left++;
            right--;
            top++;
            bottom--;
        }

        return order;
    }



    public static void main(String[] args) {

        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        L54SpiralMatrix a = new L54SpiralMatrix();

        List<Integer> list = a.spiralOrder(matrix);

        int count = 0;
        for (Integer i : list) {
            System.out.print(i + " ");

//            count++;
//
//            if(count % matrix.length == 0){
//                System.out.println();
//            }
        }


    }
}
