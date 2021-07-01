package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例 1：
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 *
 * 示例 3：
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 */
public class L100SameTree {

    /**
     * 法一: 深度优先搜索
     * 递归 + 回溯
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    /**
     * 广度优先搜索
     * 使用两个队列分别存储两个二叉树的节点。
     * 初始时将两个二叉树的根节点分别加入两个队列。每次从两个队列各取出一个节点，进行如下比较操作。
     */
    public boolean isSameTree1(TreeNode p, TreeNode q) {

        if(p == null && q == null){
            return true;
        }else if(p == null || q == null){
            return false;
        }

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.offer(p);
        queue2.offer(q);

        while(!queue1.isEmpty() && !queue2.isEmpty()){
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();

            assert node2 != null;
            if(node1.val != node2.val){
                return false;
            }

            TreeNode left1 = node1.left;
            TreeNode right1 = node1.right;

            TreeNode left2 = node2.left;
            TreeNode right2 = node2.right;

            if(left1 == null ^ left2 == null){
                return false;
            }

            if(right1 == null ^ right2 == null){
                return false;
            }

            if(left1 != null){
                queue1.offer(left1);
            }

            if(right1 != null){
                queue1.offer(right1);
            }

            if(left2 != null){
                queue2.offer(left2);
            }

            if(right2 != null){
                queue2.offer(right2);
            }
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }

    public static void main(String[] args) {

        Integer[] value1 = {1,2};
        Integer[] value2 = {1,null,2};

        TreeNode p = TreeNodes.createTreeNodeByLevel(value1);
        TreeNode q = TreeNodes.createTreeNodeByLevel(value2);

        L100SameTree a = new L100SameTree();

        System.out.println(a.isSameTree(p, q));

        System.out.println("p: ");
        TreeNodes.show(p);

        System.out.println("q: ");
        TreeNodes.show(q);
    }
}
