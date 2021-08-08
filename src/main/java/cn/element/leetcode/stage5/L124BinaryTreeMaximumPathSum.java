package cn.element.leetcode.stage5;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

/**
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 */
public class L124BinaryTreeMaximumPathSum {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        maxGain(root);

        return maxSum;
    }

    public int maxGain(TreeNode root) {

        if(root == null){
            return 0;
        }

        /*
         * 递归计算左右子节点的最大贡献值
         * 只有在最大贡献值大于0时,才会选取对应子节点
         */
        int leftGain = Math.max(maxGain(root.left), 0);
        int rightGain = Math.max(maxGain(root.right), 0);

        //节点的最大路径和取决于该节点的值与该节点的左右节点的最大贡献值
        int priceNewPath = root.val + leftGain + rightGain;

        maxSum = Math.max(maxSum, priceNewPath);  //更新答案

        return root.val + Math.max(leftGain, rightGain);  //返回节点的最大贡献值
    }

    public static void main(String[] args) {

        L124BinaryTreeMaximumPathSum a = new L124BinaryTreeMaximumPathSum();

        Integer[] value = {1,2,3};

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        TreeNodes.show(root);

        System.out.println(a.maxPathSum(root));
    }

}
