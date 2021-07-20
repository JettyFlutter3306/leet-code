package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层序遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class L107BinaryTreeLevel {

    /**
     * 广度优先遍历
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> levelOrder = new LinkedList<>();

        if(root == null){
            return levelOrder;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                level.add(node.val);

                TreeNode left = node.left;
                TreeNode right = node.right;

                if(left != null){
                    queue.offer(left);
                }

                if(right != null){
                    queue.offer(right);
                }
            }

            levelOrder.add(0, level);
        }

        return levelOrder;
    }

    public static void main(String[] args) {

        L107BinaryTreeLevel a = new L107BinaryTreeLevel();

        Integer[] value = {3,9,20,null,null,15,7};

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        TreeNodes.show(root);

        List<List<Integer>> lists = a.levelOrderBottom(root);

        lists.forEach(System.out::println);
    }

}
