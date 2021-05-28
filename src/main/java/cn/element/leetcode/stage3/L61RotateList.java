package cn.element.leetcode.stage3;

import cn.element.leetcode.common.ListNode;

/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动k个位置。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 */
public class L61RotateList {

    /**
     * 求了链表长度之后就不需要快慢指针了
     */
    public ListNode rotateRight(ListNode head, int k) {

        if(head == null || head.next == null){
            return head;
        }

        ListNode p = head;

        int size = 1;

        while(p.next != null){  //先求出链表的长度
            p = p.next;

            size++;
        }

        k %= size;  //让k对size进行取余操作,因为右移k个位置会有循环往复的情况

        if(k == 0){  //刚好除尽就不需要往下进行,直接返回head
            return head;
        }

        ListNode q = head;

        for (int i = 0; i < size - k - 1; i++) {  //让q指针先走 size - k 步
            q = q.next;
        }

        p.next = head;  //此时p指针已经到达最后一个结点位置,让p指针后继指向头结点

        head = q.next;  //此时q指针在k-1位置,让头指针指向q指针的后继结点

        q.next = null;  //q指针后继置空

        return head;
    }

    public static void main(String[] args) {

        L61RotateList a = new L61RotateList();

        int[] arr = new int[]{1,2,3,4,5};

        ListNode head = new ListNode();

        ListNode p = head;

        for (int i : arr) {
            p.next = new ListNode(i);

            p = p.next;
        }

        System.out.println(a.rotateRight(head.next, 2));  //[4, 5, 1, 2, 3]


    }
}
