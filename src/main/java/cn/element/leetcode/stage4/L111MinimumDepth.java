package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

/**
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 *
 * 示例 2：
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 */
public class L111MinimumDepth {

    public int minDepth(TreeNode root) {

        if(root == null){
            return 0;
        }

        if(root.left == null && root.right == null){
            return 1;
        }

        int ans = Integer.MAX_VALUE;

        if(root.left != null){
            ans = Math.min(minDepth(root.left), ans);
        }

        if(root.right != null){
            ans = Math.min(minDepth(root.right), ans);
        }

        return ans + 1;
    }

    public static void main(String[] args) {

        L111MinimumDepth a = new L111MinimumDepth();

        Integer[] value = new Integer[]{3,9,20,null,null,15,7};

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        TreeNodes.show(root);

        System.out.println(a.minDepth(root));
    }
}
