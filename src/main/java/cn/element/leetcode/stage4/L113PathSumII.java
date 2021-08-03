package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，
 * 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 *
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 */
public class L113PathSumII {

    List<List<Integer>> ret = new LinkedList<>();
    Deque<Integer> path = new LinkedList<>();

    /**
     * 法一: 深度优先搜索
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        dfs(root, targetSum);

        return ret;
    }

    public void dfs(TreeNode root, int targetSum) {

        if(root == null){
            return;
        }

        path.offerLast(root.val);

        targetSum -= root.val;

        if(root.left == null && root.right == null && targetSum == 0){
            ret.add(new LinkedList<>(path));
        }

        dfs(root.left, targetSum);
        dfs(root.right, targetSum);

        path.pollLast();
    }

    public static void main(String[] args) {

        L113PathSumII a = new L113PathSumII();

        Integer[] value = {5,4,8,11,null,13,4,7,2,null,null,5,1};

        int targetSum = 22;

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        TreeNodes.show(root);

        List<List<Integer>> lists = a.pathSum(root, targetSum);

        lists.forEach(System.out::println);
    }
}
