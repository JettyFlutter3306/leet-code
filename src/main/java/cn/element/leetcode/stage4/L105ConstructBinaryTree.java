package cn.element.leetcode.stage4;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder  = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class L105ConstructBinaryTree {

    private Map<Integer,Integer> map;

    /**
     * 只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。
     * 由于同一颗子树的前序遍历和中序遍历的长度显然是相同的，
     * 因此我们就可以对应到前序遍历的结果中，对上述形式中的所有左右括号进行定位。
     * 这样以来，我们就知道了左子树的前序遍历和中序遍历结果，
     * 以及右子树的前序遍历和中序遍历结果，我们就可以递归地对构造出左子树和右子树，
     * 再将这两颗子树接到根节点的左右位置。
     * 在中序遍历中对根节点进行定位时，一种简单的方法是直接扫描整个中序遍历的结果并找出根节点，
     * 但这样做的时间复杂度较高。我们可以考虑使用哈希表来帮助我们快速地定位根节点。
     * 对于哈希映射中的每个键值对，键表示一个元素（节点的值），值表示其在中序遍历中的出现位置。
     * 在构造二叉树的过程之前，我们可以对中序遍历的列表进行一遍扫描，就可以构造出这个哈希映射。
     * 在此后构造二叉树的过程中，我们就只需要 O(1) 的时间对根节点进行定位了。
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        int n = preorder.length;

        map = new HashMap<>();  //构造哈希表快速查询

        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        return buildTreeHelper(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {

        if(preorderLeft > preorderRight){
            return null;
        }

        int preorderRoot = preorderLeft;  //前序遍历中的第一个结点就是根结点

        int inorderRoot = map.get(preorder[preorderRoot]);  //在中序遍历中定位根结点

        TreeNode root = new TreeNode(preorder[preorderRoot]);  //先把根结点建立起来

        int sizeOfLeft = inorderRoot - inorderLeft;  //得到左子树的结点数目

        /*
         * 递归构造左子树,并连接到根结点
         * 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
         */
        root.left = buildTreeHelper(preorder, inorder, preorderLeft + 1, preorderLeft + sizeOfLeft, inorderLeft, inorderRoot - 1);

        /*
         * 递归地构造右子树，并连接到根节点
         * 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
         */
        root.right = buildTreeHelper(preorder, inorder, preorderLeft + sizeOfLeft + 1, preorderRight, inorderRoot + 1, inorderRight);

        return root;
    }

    public static void main(String[] args) {

        L105ConstructBinaryTree a = new L105ConstructBinaryTree();

        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        TreeNode root = a.buildTree(preorder, inorder);

        TreeNodes.show(root);
    }
}
