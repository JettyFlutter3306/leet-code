package cn.element.leetcode.stage1;

import cn.element.leetcode.common.ListNode;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 */
public class L19RemoveNthNodeFromEndOfList {

    //方法一,计算链表长度,删除倒数第n个结点就是删除正序 L-n+1 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if(head.next == null){
            return null;
        }

        ListNode p = head;

        int size = 0;

        while(p != null){
            p = p.next;

            size++;
        }

        if(size == n){
            head = head.next;

            return head;
        }

        p = head;

        for (int i = 0; i < size - n - 1; i++) {
            p = p.next;
        }

        p.next = p.next.next;

        return head;
    }

    //方法2,快慢指针法,相比上面而言只需要一次遍历
    public ListNode removeNthFromEnd1(ListNode head, int n) {

        if(head == null){
            return head;
        }

        ListNode runner = head;
        ListNode chaser = head;

        while(n-- > 0){
            runner = runner.next;
        }

        if(runner == null){
            return head.next;
        }

        while(runner.next != null){
            runner = runner.next;
            chaser = chaser.next;
        }

        chaser.next = chaser.next.next;

        return head;
    }

    public static void main(String[] args) {

        L19RemoveNthNodeFromEndOfList a = new L19RemoveNthNodeFromEndOfList();

        int[] arr = {1,2,3,4,5};

        ListNode head = new ListNode(arr[0]);

        ListNode p = head;

        for (int i = 1; i < arr.length; i++) {
            p.next = new ListNode(arr[i]);

            p = p.next;
        }

        System.out.println(a.removeNthFromEnd1(head, 2));
    }
}
