package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 */
public class L101SymmetricTree {

    /**
     * 法一: 深度遍历(递归)
     * 比较左子树和镜像右子树是否相同
     */
    public boolean isSymmetric(TreeNode root) {

        return check1(root, root);
    }

    public boolean check1(TreeNode p, TreeNode q) {

        if(p == null && q == null){
            return true;
        }

        if(p == null || q == null){
            return false;
        }

        return p.val == q.val && check1(p.left, q.right) && check1(p.right, q.left);
    }

    /**
     * 广度遍历(队列)
     */
    public boolean isSymmetric1(TreeNode root) {

        return check2(root, root);
    }

    public boolean check2(TreeNode p, TreeNode q) {

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(p);
        queue.offer(q);

        while(!queue.isEmpty()){
            p = queue.poll();
            q = queue.poll();

            if(p == null && q == null){
                continue;
            }

            if((p == null || q == null) || (p.val != q.val)){
                return false;
            }

            queue.offer(p.left);
            queue.offer(q.right);

            queue.offer(p.right);
            queue.offer(q.left);
        }

        return true;
    }

    public static void main(String[] args) {

        L101SymmetricTree a = new L101SymmetricTree();

        Integer[] value = {1,2,2,null,3,null,3};

        TreeNode root = TreeNodes.createTreeNodeByLevel(value);

        System.out.println(a.isSymmetric1(root));

        TreeNodes.show(root);
    }
}
