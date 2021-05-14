package cn.element.leetcode.stage2;

import java.util.*;

/**
 * n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
 * 每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[["Q"]]
 */
public class L51NQueen {

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> lists = new ArrayList<>();

        int[] queens = new int[n];

        Arrays.fill(queens,-1);

        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();

        backTrack(lists,queens,n,0,columns,diagonals1,diagonals2);

        return lists;
    }

    /**
     * 回溯
     */
    public void backTrack(List<List<String>> lists, int[] queens, int n, int row, Set<Integer> columns,Set<Integer> diagonals1,Set<Integer> diagonals2){

        if(row == n){
            List<String> board = generateBoard(queens,n);

            lists.add(board);
        }else{
            for (int i = 0; i < n; i++) {
                if(columns.contains(i)){  //判断是否在一个列上面
                    continue;
                }

                int diagonal1 = row - i;

                if(diagonals1.contains(diagonal1)){  //判断是否在主对角线以及与之平行的线上
                    continue;
                }

                int diagonal2 = row + i;

                if(diagonals2.contains(diagonal2)){  //判断是否在副对角线以及与之平行的线上
                    continue;
                }

                queens[row] = i;

                columns.add(i);

                diagonals1.add(diagonal1);  //主对角线横纵坐标差集合
                diagonals2.add(diagonal2);  //副对角线横纵坐标和集合

                backTrack(lists,queens,n,row + 1,columns,diagonals1,diagonals2);

                queens[row] = -1;

                columns.remove(i);  //回溯,状态重置

                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }

    }

    public List<String> generateBoard(int[] queens,int n){

        List<String> board = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char[] row = new char[n];

            Arrays.fill(row,'.');

            row[queens[i]] = 'Q';

            board.add(new String(row));
        }

        return board;
    }

    public static void main(String[] args) {

        L51NQueen a = new L51NQueen();

        List<List<String>> lists = a.solveNQueens(8);

        int i = 1;

        for (List<String> list : lists) {
            System.out.println("第" + i++ +"个解:");

            for (String s : list) {
                System.out.println(s);
            }

            System.out.println("==========================");
        }

    }

}
