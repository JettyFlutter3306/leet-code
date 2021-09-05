package cn.element.leetcode.stage5;

import cn.element.leetcode.common.ListNode;
import cn.element.leetcode.common.ListNodes;

/**
 * 给你链表的头结点head，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 * 你可以在O(n * log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 */
public class L148SortList {

    /**
     * 最适合链表的排序算法是归并排序
     * 归并排序基于分治算法,最容易想到的实现方式是自顶向下的递归实现,考虑到递归调用的栈空间,自顶向下归并排序的空间
     * 复杂度是O(log n),如果要达到O(1)的时间复杂度,则需要使用自底向上的方式
     *
     * 自顶向下归并排序:
     *      1.找到链表的中点,以中点为分界,将链表拆分两个子链表,寻找链表的中点可以使用快慢指针的做法,快指针每次移动2步,
     *        慢指针每次移动1步,当快指针到达链表末尾时,慢指针指向的链表节点即为链表的中点
     *      2.对两个子链表分别排序
     *      3.将两个排序后的子链表合并,得到完整的排序后的链表
     * 上述的过程可以通过递归实现,递归的终止条件是链表的节点数小于或等于1,即当链表为空或者链表只包含一个节点,
     * 不需要对链表进行拆分和排序
     */
    public ListNode sortList(ListNode head) {

        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {

        if (head == null) {
            return head;
        }

        if (head.next == tail) {
            head.next = null;

            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;

            if (fast != tail) {
                fast = fast.next;
            }
        }

        ListNode mid = slow;

        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);

        return merge(list1, list2);
    }

    private ListNode merge(ListNode head1, ListNode head2) {

        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead;
        ListNode temp1 = head1;
        ListNode temp2 = head2;

        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }

            temp = temp.next;
        }

        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {

        L148SortList a = new L148SortList();

        int[] value = {4,2,1,3};

        ListNode head = ListNodes.createListNode(value);

        System.out.println(head.next);

        ListNode node = a.sortList(head.next);

        System.out.println(node);
    }
}
