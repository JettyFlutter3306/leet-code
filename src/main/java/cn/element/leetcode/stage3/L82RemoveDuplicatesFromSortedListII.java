package cn.element.leetcode.stage3;

import cn.element.leetcode.common.ListNode;
import cn.element.leetcode.common.ListNodes;

/**
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，
 * 只保留原始链表中没有重复出现的数字。
 * 返回同样按升序排列的结果链表。
 *
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 */
public class L82RemoveDuplicatesFromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {

        if(head == null){
            return head;
        }

        ListNode dummy = new ListNode(-1,head);

        ListNode cur = dummy;

        while(cur.next != null && cur.next.next != null){
            if(cur.next.val == cur.next.next.val){
                int x = cur.next.val;

                while(cur.next != null && cur.next.val == x){
                    cur.next = cur.next.next;
                }
            }else{
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1,2,3,3,4,4,5};

        ListNode head = ListNodes.createListNode(arr);

        L82RemoveDuplicatesFromSortedListII a = new L82RemoveDuplicatesFromSortedListII();

        System.out.println(a.deleteDuplicates(head.next));
    }
}
