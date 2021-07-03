package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class L102BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> lists = new ArrayList<>();

        if(root == null){
            return lists;
        }

        Queue<TreeNode> queue = new LinkedList<>();  //定义一个队列

        queue.offer(root);

        while(!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();

            int currentSize = queue.size();

            for (int i = 1; i <= currentSize; i++) {
                TreeNode node = queue.poll();

                list.add(node.val);

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }
            }

            lists.add(list);
        }

        return lists;
    }

    public static void main(String[] args) {

        L102BinaryTreeLevelOrderTraversal a = new L102BinaryTreeLevelOrderTraversal();

        Integer[] value = {3,9,20,null,null,15,7};

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        TreeNodes.show(root);

        List<List<Integer>> lists = a.levelOrder(root);

        lists.forEach(System.out::println);
    }
}
