package cn.element.leetcode.stage2;

import java.util.HashMap;

/**
 * 请你判断一个9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 *
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 *
 * 注意：
 *
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 *
 * 示例 1：
 * 输入：board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：true
 *
 * 示例 2：
 * 输入：board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：false
 * 解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 */
public class L36ValidSudoku {

    public boolean isValidSudoku(char[][] board) {

        //创建三个HashMap数组,容量为9,用于记录数字出现的次数
        HashMap<Integer,Integer>[] rows = new HashMap[9];
        HashMap<Integer,Integer>[] columns = new HashMap[9];
        HashMap<Integer,Integer>[] boxes = new HashMap[9];

        for (int i = 0; i < 9; i++) {  //对数组进行逐个赋值
            rows[i] = new HashMap<>();
            columns[i] = new HashMap<>();
            boxes[i] = new HashMap<>();
        }

        for (int i = 0; i < 9; i++) {  //遍历 9 * 9 数独,一次遍历
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];  //拿到对应的字符

                if(num != '.'){
                    int n = (int) num;  //拿到矩阵对应的数字

                    int boxIndex = (i / 3) * 3 + j / 3;  //获得九宫格的位置索引

                    rows[i].put(n,rows[i].getOrDefault(n,0) + 1);  //记录一行数字的出现次数

                    columns[j].put(n,columns[j].getOrDefault(n,0) + 1);  //记录一列数字出现的次数

                    boxes[boxIndex].put(n,boxes[boxIndex].getOrDefault(n,0) + 1);  //记录一个九宫格内数字出现的次数

                    if(rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[boxIndex].get(n) > 1){  //假如有一个数字出现了超过一次以上那么就返回false
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        char[][] board = new char[][]{
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        L36ValidSudoku a = new L36ValidSudoku();

        System.out.println(a.isValidSudoku(board));
    }
}
