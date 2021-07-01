package cn.element.leetcode.common;

/**
 * 二叉树结点类
 */
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {

        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {

        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("[");

        toString(sb, this);

        sb.deleteCharAt(sb.length() - 1);

        sb.append("]");

        return sb.toString();
    }

    private void toString(StringBuilder sb, TreeNode node) {

        sb.append(node.val).append(",");

        if(node.left != null){
            toString(sb, node.left);
        }else{
            sb.append("^").append(",");
        }

        if(node.right != null){
            toString(sb, node.right);
        }else{
            sb.append("^").append(",");
        }
    }

    /**
     * 获取二叉树的高度
     */
    public int height() {

        return height(this);
    }

    private int height(TreeNode p) {

        int h = 0;

        if(p != null){
            h = Math.max(height(p.left),h);

            h = Math.max(height(p.right),h);
        }

        return h + 1;
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(1);
        node.left.right = new TreeNode(3);
        node.right = new TreeNode(7);
        node.right.left = new TreeNode(15);

        System.out.println(node);
    }


}
