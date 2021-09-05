package cn.element.leetcode.stage5;

import cn.element.leetcode.common.ListNode;
import cn.element.leetcode.common.ListNodes;

/**
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 示例2：
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class L147InsertionSortList {

    /**
     * 从前往后找插入点
     * 插入排序的基本思想是,维护一个有序序列,初始时有序序列只有一个元素,每次将一个新的元素插入到有序序列中
     * 将有序序列的长度+1,直到全部元素都加入到有序序列中
     * 如果是数组的插入排序,则数组的前面部分是有序序列,每次找到有序序列后面的第一个元素(待插入元素)的插入位置
     * 将有序序列中的插入位置后面的元素都往后移动一位,然后将待插入元素置于插入位置
     * 对于链表而言,插入元素只要更新相邻节点的指针即可,不需要像数组一样将插入位置后面的元素往后移动,
     * 因此插入操作的时间复杂度是O(1),但是找到插入位置需要遍历链表中的节点,时间复杂度是O(n)
     * 因此对链表插入排序的总时间复杂度是O(n²),其中n是链表的长度
     */
    public ListNode insertionSortList(ListNode head) {

        if (head == null) {
            return head;
        }

        ListNode dummyHead = new ListNode(0);

        dummyHead.next = head;

        ListNode lastSorted = head;
        ListNode curr = head.next;

        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;

                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }

                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }

            curr = lastSorted.next;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {

        L147InsertionSortList a = new L147InsertionSortList();

        int[] value = {4,2,1,3};

        ListNode head = ListNodes.createListNode(value);

        System.out.println(head.next);

        System.out.println(a.insertionSortList(head.next));
    }
}
