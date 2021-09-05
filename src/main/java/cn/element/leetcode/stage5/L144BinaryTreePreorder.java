package cn.element.leetcode.stage5;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根节点 root ，返回它节点值的前序遍历。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 */
public class L144BinaryTreePreorder {

    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        if (root != null) {
            dfs(root, list);
        }

        return list;
    }

    private void dfs(TreeNode root, List<Integer> list) {

        list.add(root.val);

        if (root.left != null) {
            dfs(root.left, list);
        }

        if (root.right != null) {
            dfs(root.right, list);
        }
    }

    public static void main(String[] args) {

        L144BinaryTreePreorder a = new L144BinaryTreePreorder();

        Integer[] value = {1,null,2,3};

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        TreeNodes.show(root);

        System.out.println(a.preorderTraversal(root));
    }


}
