package cn.element.leetcode.stage3;

/**
 * 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 */
public class L74SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            if(target == matrix[i][n - 1]){
                return true;
            }else if(target < matrix[i][n - 1]){  //二分查找
                int left = 0;
                int right = n - 1;
                int mid = (left + right) / 2;

                while(left <= right){
                    if(matrix[i][mid] < target){
                        left = mid + 1;
                        mid = (left + right) / 2;
                    }else if(matrix[i][mid] > target){
                        right = mid - 1;
                        mid = (left + right) / 2;
                    }else{
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        L74SearchA2DMatrix a = new L74SearchA2DMatrix();

        int[][] matrix = new int[][]{
                {1,3,5},
        };

        int target = 1;

        System.out.println(a.searchMatrix(matrix, target));

    }

}
