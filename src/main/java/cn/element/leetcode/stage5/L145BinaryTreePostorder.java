package cn.element.leetcode.stage5;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶:递归算法很简单，你可以通过迭代算法完成吗？
 */
public class L145BinaryTreePostorder {

    /**
     * 递归法省略
     * 使用迭代法
     */
    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode prev = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);

                root = root.left;
            }

            root = stack.pop();

            if (root.right == null || root.right == prev) {
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }

        return list;
    }

    public static void main(String[] args) {

        L145BinaryTreePostorder a = new L145BinaryTreePostorder();

        Integer[] value = {1,null,2,3};

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        TreeNodes.show(root);

        System.out.println(a.postorderTraversal(root));
    }
}
