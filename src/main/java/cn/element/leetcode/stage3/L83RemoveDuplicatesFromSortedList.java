package cn.element.leetcode.stage3;

import cn.element.leetcode.common.ListNode;
import cn.element.leetcode.common.ListNodes;

/**
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * 返回同样按升序排列的结果链表。
 *
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 */
public class L83RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {

        if(head == null){
            return head;
        }

        ListNode cur = head;

        while(cur.next != null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {

        L83RemoveDuplicatesFromSortedList a = new L83RemoveDuplicatesFromSortedList();

        int[] arr = {1,1,2,3,3};

        ListNode head = ListNodes.createListNode(arr);

        System.out.println(a.deleteDuplicates(head.next));
    }
}
