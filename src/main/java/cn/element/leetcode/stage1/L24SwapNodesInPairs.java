package cn.element.leetcode.stage1;

import cn.element.leetcode.common.ListNode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 */
public class L24SwapNodesInPairs {

    /**
     * 使用递归,将两两交换head链表元素转化为 两两交换head.next.next链表元素子问题
     */
    public ListNode swapPairs(ListNode head) {

        if(head == null || head.next == null){
            return head;
        }

        ListNode newHead = head.next;

        head.next = swapPairs(newHead.next);

        newHead.next = head;

        return newHead;
    }

    /**
     * 使用迭代
     */
    public ListNode swapPairs1(ListNode head){

        ListNode dummyNode = new ListNode(-1);  //定义一个哑结点

        dummyNode.next = head;  //哑结点的后继指针指向head

        ListNode temp = dummyNode;  //定义temp引用指向哑结点

        while(temp.next != null && temp.next.next != null){
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;

            temp.next = node2;

            node1.next = node2.next;
            node2.next = node1;

            temp = node1;
        }

        return dummyNode.next;
    }

    public static void main(String[] args) {

        int[] arr = {1,2,3,4};

        ListNode head = new ListNode(arr[0]);

        ListNode p = head;

        for (int i = 1; i < arr.length; i++) {
            p.next = new ListNode(arr[i]);

            p = p.next;
        }

        L24SwapNodesInPairs a = new L24SwapNodesInPairs();

        System.out.println(a.swapPairs(head));

    }
}
