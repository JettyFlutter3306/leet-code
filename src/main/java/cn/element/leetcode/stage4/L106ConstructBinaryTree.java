package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class L106ConstructBinaryTree {

    int post_idx;
    int[] postorder;
    int[] inorder;
    Map<Integer, Integer> idx_map = new HashMap<>();

    public TreeNode helper(int in_left, int in_right) {

        if (in_left > in_right) {  // 如果这里没有节点构造二叉树了，就结束
            return null;
        }

        int root_val = postorder[post_idx];  // 选择 post_idx 位置的元素作为当前子树根节点

        TreeNode root = new TreeNode(root_val);

        int index = idx_map.get(root_val);  // 根据 root 所在位置分成左右两棵子树

        post_idx--;  // 下标减一

        root.right = helper(index + 1, in_right);  // 构造右子树
        root.left = helper(in_left, index - 1);  // 构造左子树

        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        this.postorder = postorder;
        this.inorder = inorder;

        post_idx = postorder.length - 1;  // 从后序遍历的最后一个元素开始

        int idx = 0;  // 建立（元素，下标）键值对的哈希表

        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }

        return helper(0, inorder.length - 1);
    }

    public static void main(String[] args) {

        L106ConstructBinaryTree a = new L106ConstructBinaryTree();

        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};

        TreeNode root = a.buildTree(inorder, postorder);

        TreeNodes.show(root);
    }
}
