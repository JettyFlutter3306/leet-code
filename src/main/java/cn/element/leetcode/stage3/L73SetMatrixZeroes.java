package cn.element.leetcode.stage3;

import java.util.Arrays;

/**
 * 给定一个m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * 进阶：
 * 一个直观的解决方案是使用 O(mn)的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m+n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 *
 * 示例 1：
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 *
 * 示例 2：
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 */
public class L73SetMatrixZeroes {

    /**
     * 法一: 使用标记数组
     * 我们可以使用两个标记数组分别记录每一行和每一列是否有 0 出现
     * 具体地,我们首先遍历改数组一次,如果某个元素为 0 ,那么就将改元素所在的行和列对应标记数组的位置置为true
     * 最后我们再次遍历该数组,用标记数组更新原数组即可
     */
    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        //声明两个标记数组,用于表示对应行列是否为0
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == 0){
                    row[i] = col[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(row[i] || col[j]){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 法二: 使用一个标记变量
     * 只使用一个标记变量记录第一列是否原本存在 0,这样,第一列的第一个元素即可以
     * 标记第一行是否出现 0,但是为了防止每一列的第一个元素被提前更新,我们需要从最后一行开始
     * 倒序地处理矩阵元素
     */
    public void setZeroes1(int[][] matrix){

        int m = matrix.length;
        int n = matrix[0].length;

        boolean flagCol0 = false;

        for (int i = 0; i < m; i++) {
            if(matrix[i][0] == 0){
                flagCol0 = true;
            }

            for (int j = 1; j < n; j++) {
                if(matrix[i][j] == 0){
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }

            if(flagCol0){
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {

        L73SetMatrixZeroes a = new L73SetMatrixZeroes();

        int[][] arr = new int[][]{
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };

        a.setZeroes1(arr);

        for (int[] i : arr) {
            System.out.println(Arrays.toString(i));
        }
    }
}
