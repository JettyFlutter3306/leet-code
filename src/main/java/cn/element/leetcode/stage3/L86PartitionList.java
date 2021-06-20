package cn.element.leetcode.stage3;

import cn.element.leetcode.common.ListNode;
import cn.element.leetcode.common.ListNodes;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 *
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 */
public class L86PartitionList {

    public ListNode partition(ListNode head, int x) {

        ListNode small = new ListNode(0);
        ListNode smallHead = small;

        ListNode large = new ListNode(0);
        ListNode largeHead = large;

        while (head != null) {
            if (head.val < x) {
                small.next = head;

                small = small.next;
            } else {
                large.next = head;

                large = large.next;
            }

            head = head.next;
        }

        large.next = null;

        small.next = largeHead.next;

        return smallHead.next;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1,4,3,2,5,2};

        ListNode head = ListNodes.createListNode(arr);

        L86PartitionList a = new L86PartitionList();

        System.out.println(a.partition(head.next, 3));

    }
}
