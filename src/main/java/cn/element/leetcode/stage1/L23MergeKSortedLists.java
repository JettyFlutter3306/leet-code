package cn.element.leetcode.stage1;

import cn.element.leetcode.common.ListNode;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 */
public class L23MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {

        ListNode ans = null;

        for (ListNode list : lists) {
            ans = mergeTwoLists(ans, list);
        }

        return ans;
    }

    public ListNode mergeTwoLists(ListNode a,ListNode b){

        ListNode temp = new ListNode(-1);
        ListNode p = temp;

        while(a != null && b != null){
            if(a.val > b.val){
                p.next = b;

                b = b.next;
            }else{
                p.next = a;

                a = a.next;
            }

            p = p.next;
        }

        p.next = a == null ? b : a;

        return temp.next;
    }

    public static void main(String[] args) {

    }

}
