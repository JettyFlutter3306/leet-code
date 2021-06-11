package cn.element.leetcode.stage3;

/**
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 *
 * 示例 2：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 *
 * 示例 3：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 */
public class L79WordSearch {

    private static final int[][] DIRECTIONS = {{-1,0},{0,-1},{0,1},{1,0}};
    private int rows;
    private int cols;
    private int len;
    private boolean[][] visited;
    private char[] charArray;
    private char[][] board;

    /**
     * 考虑回溯算法
     * 按照 "上" "右" "下" "左" 的方向搜索
     */
    public boolean exist(char[][] board, String word) {

        this.rows = board.length;

        if(rows == 0){
            return false;
        }

        this.cols = board[0].length;
        this.visited = new boolean[rows][cols];
        this.len = word.length();
        this.charArray = word.toCharArray();
        this.board = board;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(dfs(i,j,0)){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int x,int y,int begin){

        if(begin == this.len - 1){
            return this.board[x][y] == this.charArray[begin];
        }

        if(board[x][y] == this.charArray[begin]){
            this.visited[x][y] = true;

            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if(this.inArea(newX,newY) && !this.visited[newX][newY]){
                    if(dfs(newX,newY,begin + 1)){
                        return true;
                    }
                }
            }

            this.visited[x][y] = false;
        }

        return false;
    }

    private boolean inArea(int x,int y){

        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static void main(String[] args) {

        L79WordSearch a = new L79WordSearch();

        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };

        String word = "SEE";

        System.out.println(a.exist(board, word));

    }
}
