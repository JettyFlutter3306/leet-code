package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 */
public class L114FlattenBinaryTree {

    /**
     * 将二叉树展开为单链表之后，单链表中的节点顺序即为二叉树的前序遍历访问各节点的顺序。
     * 因此，可以对二叉树进行前序遍历，获得各节点被访问到的顺序。
     * 由于将二叉树展开为链表之后会破坏二叉树的结构，
     * 因此在前序遍历结束之后更新每个节点的左右子节点的信息，将二叉树展开为单链表。
     */
    public void flatten(TreeNode root) {

        List<TreeNode> list = new ArrayList<>();

        preorderTraverse(root, list);

        int size = list.size();

        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);

            prev.left = null;
            prev.right = curr;
        }

    }

    public void preorderTraverse(TreeNode root, List<TreeNode> list) {

        if(root != null){
            list.add(root);

            preorderTraverse(root.left, list);
            preorderTraverse(root.right, list);
        }
    }

    public static void main(String[] args) {

        L114FlattenBinaryTree a = new L114FlattenBinaryTree();

        Integer[] value = new Integer[]{1,2,5,3,4,null,6};

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        TreeNodes.show(root);

        a.flatten(root);

        System.out.print(root.val + "\t");

        while(root.right != null) {
            System.out.print(root.right.val + "\t");

            root = root.right;
        }
    }
}
