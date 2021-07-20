package cn.element.leetcode.stage4;

import cn.element.leetcode.common.ListNode;
import cn.element.leetcode.common.ListNodes;
import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class L109ConvertSortedList {

    /**
     * 具体地，设当前链表的左端点为 left，右端点 right，包含关系为「左闭右开」，
     * 即 left 包含在链表中而 right 不包含在链表中。我们希望快速地找出链表的中位数节点 mid
     * 找出链表中位数节点的方法多种多样，其中较为简单的一种是「快慢指针法」。
     * 初始时，快指针 fast 和慢指针 slow 均指向链表的左端点 left。我们将快指针 fast 向右移动两次的同时，
     * 将慢指针 slow 向右移动一次，直到快指针到达边界（即快指针到达右端点或快指针的下一个节点是右端点）。
     * 此时，慢指针对应的元素就是中位数。
     * 在找出了中位数节点之后，我们将其作为当前根节点的元素，并递归地构造其左侧部分的链表对应的左子树，
     * 以及右侧部分的链表对应的右子树。
     */
    public TreeNode sortedListToBST(ListNode head) {

        return buildTree(head, null);
    }

    public TreeNode buildTree(ListNode left, ListNode right) {

        if(left == right){
            return null;
        }

        ListNode mid = getMid(left, right);

        TreeNode root = new TreeNode(mid.val);

        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);

        return root;
    }

    public ListNode getMid(ListNode left, ListNode right) {

        ListNode fast = left;
        ListNode slow = left;

        while(fast != right && fast.next != right){
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args) {

        L109ConvertSortedList a = new L109ConvertSortedList();

        int[] value = {-10, -3, 0, 5, 9};

        ListNode head = ListNodes.createListNode(value);

        TreeNode treeNode = a.sortedListToBST(head.next);

        TreeNodes.show(treeNode);
    }
}
