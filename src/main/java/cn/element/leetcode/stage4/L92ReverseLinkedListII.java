package cn.element.leetcode.stage4;

import cn.element.leetcode.common.ListNode;
import cn.element.leetcode.common.ListNodes;

/**
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 */
public class L92ReverseLinkedListII {

    /**
     * 一次遍历,头插法
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode dummyNode = new ListNode(-1);  //设置哑结点是这一类问题的一般做法

        dummyNode.next = head;  //后继指向头结点

        ListNode pre = dummyNode;  //定义前驱指针指向哑结点

        for (int i = 0; i < left - 1; i++) {  //pre指针移动left - 1步
            pre = pre.next;
        }

        ListNode cur = pre.next;

        ListNode suc;

        for (int i = 0; i < right - left; i++) {  //每次把后面一个结点插入前面一个结点,即可完成反转
            suc = cur.next;
            cur.next = suc.next;
            suc.next = pre.next;
            pre.next = suc;
        }

        return dummyNode.next;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1,2,3,4,5};

        ListNode head = ListNodes.createListNode(arr);

        L92ReverseLinkedListII a = new L92ReverseLinkedListII();

        System.out.println(a.reverseBetween(head.next, 2, 4));
    }
}
