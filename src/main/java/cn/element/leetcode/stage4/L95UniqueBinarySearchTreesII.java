package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。
 * 可以按 任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 */
public class L95UniqueBinarySearchTreesII {

    /**
     * 考虑使用回溯法
     */
    public List<TreeNode> generateTrees(int n) {

        if(n == 0){
            return new LinkedList<>();
        }

        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {

        List<TreeNode> allTrees = new LinkedList<>();

        if(start > end){
            allTrees.add(null);

            return allTrees;
        }

        for (int i = start; i <= end; i++) {  //枚举可行根结点
            List<TreeNode> leftTrees = generateTrees(start, i - 1);  //获得所有可行的左子树集合

            List<TreeNode> rightTrees = generateTrees(i + 1, end);  //获得所有可行的右子树集合

            for (TreeNode leftTree : leftTrees) {  //从左子树集合中选出一棵左子树,从右子树集合中选出一棵右子树,拼接到根结点上
                for (TreeNode rightTree : rightTrees) {
                    TreeNode currTree = new TreeNode(i);

                    currTree.left = leftTree;
                    currTree.right = rightTree;
                    allTrees.add(currTree);
                }
            }
        }

        return allTrees;
    }

    public static void main(String[] args) {

        L95UniqueBinarySearchTreesII a = new L95UniqueBinarySearchTreesII();

        System.out.println(a.generateTrees(3));
    }

}
