package cn.element.leetcode.stage2;

import java.util.ArrayList;
import java.util.List;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 *
 * 示例：
 * 输入：board = [
 *      ["5","3",".",".","7",".",".",".","."],
 *      ["6",".",".","1","9","5",".",".","."],
 *      [".","9","8",".",".",".",".","6","."],
 *      ["8",".",".",".","6",".",".",".","3"],
 *      ["4",".",".","8",".","3",".",".","1"],
 *      ["7",".",".",".","2",".",".",".","6"],
 *      [".","6",".",".",".",".","2","8","."],
 *      [".",".",".","4","1","9",".",".","5"],
 *      [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出：[
 *      ["5","3","4","6","7","8","9","1","2"],
 *      ["6","7","2","1","9","5","3","4","8"],
 *      ["1","9","8","3","4","2","5","6","7"],
 *      ["8","5","9","7","6","1","4","2","3"],
 *      ["4","2","6","8","5","3","7","9","1"],
 *      ["7","1","3","9","2","4","8","5","6"],
 *      ["9","6","1","5","3","7","2","8","4"],
 *      ["2","8","7","4","1","9","6","3","5"],
 *      ["3","4","5","2","8","6","1","7","9"]
 * ]
 *
 * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
 */
public class L37SudokuSolver {

    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<>();

    public void solveSudoku(char[][] board) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board[i][j] == '.'){
                    spaces.add(new int[]{i,j});
                }else{
                    int digit = board[i][j] - '0' - 1;

                    line[i][digit] = column[j][digit] = block[i/3][j/3][digit] = true;
                }
            }
        }

        dfs(board,0);
    }

    public void dfs(char[][] board,int pos){

        if(pos == spaces.size()){
            valid = true;

            return;
        }

        int[] space = spaces.get(pos);

        int i = space[0];
        int j = space[1];

        for (int k = 0; k < 9 && !valid; k++) {
            if(!line[i][k] && !column[j][k] && !block[i/3][j/3][k]){
                line[i][k] = column[j][k] = block[i/3][j/3][k] = true;

                board[i][j] = (char) (k + '0' + 1);

                dfs(board,pos + 1);

                line[i][k] = column[j][k] = block[i/3][j/3][k] = false;
            }
        }
    }

    public static void main(String[] args) {

        L37SudokuSolver a = new L37SudokuSolver();

        char[][] chars = new char[][]{
                {'5','3','4','6','7','8','9','1','2'},
                {'6','7','2','1','9','5','3','4','8'},
                {'1','9','8','3','4','2','5','6','7'},
                {'8','5','9','7','6','1','4','2','3'},
                {'4','2','6','8','5','3','7','9','1'},
                {'7','1','3','9','2','4','8','5','6'},
                {'9','6','1','5','3','7','2','8','4'},
                {'2','8','7','4','1','9','6','3','5'},
                {'3','4','5','2','8','6','1','7','9'}
        };

        a.solveSudoku(chars);

        for (char[] aChar : chars) {
            for (char c : aChar) {
                System.out.print(c + "\t");
            }

            System.out.println();
        }

        /*
            5	3	4	6	7	8	9	1	2
            6	7	2	1	9	5	3	4	8
            1	9	8	3	4	2	5	6	7
            8	5	9	7	6	1	4	2	3
            4	2	6	8	5	3	7	9	1
            7	1	3	9	2	4	8	5	6
            9	6	1	5	3	7	2	8	4
            2	8	7	4	1	9	6	3	5
            3	4	5	2	8	6	1	7	9
         */
    }


}
