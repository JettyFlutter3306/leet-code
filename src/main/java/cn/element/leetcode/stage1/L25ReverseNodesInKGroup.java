package cn.element.leetcode.stage1;

import cn.element.leetcode.common.ListNode;

/**
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 * k是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 示例 3：
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 *
 * 示例 4：
 * 输入：head = [1], k = 1
 * 输出：[1]
 */
public class L25ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode hair = new ListNode(-1);
        hair.next = head;
        ListNode pre = hair;

        while(head != null){
            ListNode tail = pre;

            for (int i = 0; i < k; i++) {  //查看剩余部分长度是否大于等于k
                tail = tail.next;

                if(tail == null){
                    return hair.next;
                }
            }

            ListNode nex = tail.next;
            ListNode[] reversed = this.reverse(head,tail);
            head = reversed[0];
            tail = reversed[1];

            //把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    private ListNode[] reverse(ListNode head, ListNode tail) {

        ListNode prev = tail.next;
        ListNode p = head;

        while(prev != tail){
            ListNode nex = p.next;

            p.next = prev;

            prev = p;

            p = nex;
        }

        return new ListNode[]{tail, head};
    }

    public static void main(String[] args) {

        L25ReverseNodesInKGroup a = new L25ReverseNodesInKGroup();

        int[] arr = {1,2,3,4,5};

        ListNode head = new ListNode();

        ListNode p = head;

        for (int i : arr) {
            p.next = new ListNode(i);

            p = p.next;
        }

        System.out.println(a.reverseKGroup(head.next,2));

    }
}
