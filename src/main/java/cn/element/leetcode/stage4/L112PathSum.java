package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你二叉树的根节点root 和一个表示目标和的整数targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和targetSum 。
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 *
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 */
public class L112PathSum {

    /**
     * 法一: 广度优先搜索
     * 首先我们可以想到使用使用广度优先搜索的方式,记录从根结点到当前结点的路径和,以防止重复计算
     * 我们使用两个队列,分别储存要遍历的结点,以及根结点到这些结点的路径和即可
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {

        if(root == null){
            return false;
        }

        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueVal = new LinkedList<>();

        queueNode.offer(root);
        queueVal.offer(root.val);

        while (!queueNode.isEmpty()) {
            TreeNode now = queueNode.poll();  //拿到当前结点

            int temp = queueVal.poll();  //拿到当前路径之和

            if(now.left == null && now.right == null){
                if(temp == targetSum){
                    return true;
                }

                continue;
            }

            if(now.left != null){
                queueNode.offer(now.left);
                queueVal.offer(now.left.val + temp);
            }

            if(now.right != null){
                queueNode.offer(now.right);
                queueVal.offer(now.right.val + temp);
            }
        }

        return false;
    }

    /**
     * 法二: 递归
     * 观察要求我们完成的函数,我们可以归纳出功能,询问是否存在从当前结点root到叶子结点的路径
     * 满足其路径和为sum
     * 假定从根结点到当前结点的和为val,我们可以将这个大问题转化为一个小问题是否存在从当前结点
     * 的子节点到叶子结点的路径,满足路径和为sum - val
     */
    public boolean hasPathSum1(TreeNode root, int targetSum) {

        if(root == null){
            return false;
        }

        if(root.left == null && root.right == null){
            return targetSum == root.val;
        }

        return hasPathSum1(root.left, targetSum - root.val) || hasPathSum1(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {

        L112PathSum a = new L112PathSum();

        Integer[] value = new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,1};

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        TreeNodes.show(root);

        System.out.println(a.hasPathSum1(root, 22));
    }
}
