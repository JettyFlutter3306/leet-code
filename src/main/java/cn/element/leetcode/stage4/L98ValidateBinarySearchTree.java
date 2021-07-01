package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 示例2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class L98ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {

        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 递归遍历二叉树,同时修改上下界
     * @param lower         下界
     * @param upper         上界
     */
    private boolean isValidBST(TreeNode node, long lower, long upper) {

        if(node == null){
            return true;
        }

        if(node.val <= lower || node.val >= upper){
            return false;
        }

        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

    /**
     * 法二: 中序遍历
     */
    public boolean isValidBST1(TreeNode root) {

        Deque<TreeNode> stack = new LinkedList<>();

        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);

                root = root.left;
            }

            root = stack.pop();

            if (root.val <= inorder) {  // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
                return false;
            }

            inorder = root.val;

            root = root.right;
        }

        return true;
    }

    public static void main(String[] args) {

        L98ValidateBinarySearchTree a = new L98ValidateBinarySearchTree();

        Integer[] value = {5,1,null,null,4,3,null,null,6,null,null};

        TreeNode root = TreeNodes.createTreeNode(value);

        System.out.println(a.isValidBST1(root));

        TreeNodes.show(root);
    }
}
