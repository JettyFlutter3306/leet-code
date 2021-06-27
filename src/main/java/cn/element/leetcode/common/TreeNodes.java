package cn.element.leetcode.common;

/**
 * 二叉树工具类
 */
public class TreeNodes {

    private static int i = 0;

    public static TreeNode createTreeNode(Integer[] value) {


        return null;
    }

    private static void createTreeNode(TreeNode root, Integer[] value) {

        root = new TreeNode(value[i]);

        if(i < value.length){
            createTreeNode(root.left, value);

            i++;

            createTreeNode(root.right, value);
        }
    }
}
