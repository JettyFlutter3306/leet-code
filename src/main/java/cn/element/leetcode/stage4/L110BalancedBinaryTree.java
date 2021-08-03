package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 *
 * 示例 3：
 * 输入：root = []
 * 输出：true
 */
public class L110BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {

        if(root == null){
            return true;
        }else{
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {

        if(root == null){
            return 0;
        }

        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static void main(String[] args) {

        L110BalancedBinaryTree a = new L110BalancedBinaryTree();

        Integer[] value = {3,9,20,null,null,15,7};

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        System.out.println(a.isBalanced(root));
    }
}
