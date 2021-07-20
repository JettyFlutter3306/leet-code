package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class L103BinaryTreeZigzagLevelOrderTraversal {

    /**
     * 队列问题
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();

        if(root == null){
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        boolean isOrderLeft = true;  //是否从左到右的标记

        while(!queue.isEmpty()){
            Deque<Integer> levelList = new LinkedList<>();

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if(isOrderLeft){
                    levelList.offerLast(node.val);
                }else{
                    levelList.offerFirst(node.val);
                }

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }
            }

            ans.add(new LinkedList<>(levelList));

            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }

    public static void main(String[] args) {

        L103BinaryTreeZigzagLevelOrderTraversal a = new L103BinaryTreeZigzagLevelOrderTraversal();

        Integer[] value = new Integer[]{3,9,20,null,null,15,7};

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        TreeNodes.show(root);

        List<List<Integer>> lists = a.zigzagLevelOrder(root);

        lists.forEach(System.out::println);
    }
}
