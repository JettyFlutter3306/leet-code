package cn.element.leetcode.stage5;

import cn.element.leetcode.common.ListNode;
import cn.element.leetcode.common.ListNodes;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * L0→ L1→ … → Ln-1→ Ln
 * 请将其重新排列后变为：
 *
 * L0→Ln→L1→Ln-1→L2→Ln-2→ …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 *
 * 示例 2:
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 */
public class L143ReorderList {

    /**
     * 法一: 线性表
     * 利用线性表存储该链表,然后利用线性表可以下标访问的特点,直接
     * 按顺访问指定元素,重建该该链表即可
     */
    public void reorderList(ListNode head) {

        if (head == null) {
            return;
        }

        List<ListNode> list = new ArrayList<>();
        ListNode node = head;

        while (node != null) {
            list.add(node);
            node = node.next;
        }

        int i = 0;
        int j = list.size() - 1;

        while (i < j) {
            list.get(i).next = list.get(j);

            i++;

            if (i == j) {
                break;
            }

            list.get(j).next = list.get(i);

            j--;
        }

        list.get(i).next = null;
    }

    public static void main(String[] args) {

        L143ReorderList a = new L143ReorderList();

        int[] value = {1,2,3,4,5};

        ListNode head = ListNodes.createListNode(value);

        System.out.println(head.next);

        a.reorderList(head.next);

        System.out.println(head.next);
    }
}
