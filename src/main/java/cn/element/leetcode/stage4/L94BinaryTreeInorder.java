package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树的根节点 root ，返回它的 中序遍历。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
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
 * 输出：[2,1]
 *
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 */
public class L94BinaryTreeInorder {

    /**
     * 法一: 递归法,时间复杂度O(n)
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        inorderTraversal(list, root);

        return list;
    }

    private void inorderTraversal(List<Integer> list, TreeNode root) {

        if(root != null){
            inorderTraversal(list, root.left);

            list.add(root.val);

            inorderTraversal(list, root.right);
        }
    }

    /**
     * 法二: 迭代法
     * 递归法只不过隐式地维护了一个栈,现在要把它显示地使用出来
     */
    public List<Integer> inorderTraversal1(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>(); //声明一个显示的栈

        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);

                root = root.left;
            }

            root = stack.pop();

            list.add(root.val);

            root = root.right;
        }

        return list;
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(1);
        node.left.right = new TreeNode(3);
        node.right = new TreeNode(7);
        node.right.left = new TreeNode(15);

        L94BinaryTreeInorder a = new L94BinaryTreeInorder();

        System.out.println(a.inorderTraversal1(node));
    }

}
