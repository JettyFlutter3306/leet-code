package cn.element.leetcode.stage1;

import cn.element.leetcode.common.ListNode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的.
 *
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 */
public class L21MergeTwoSortedLists {

    //暴力解法,时间复杂度O(M+N) 空间复杂度O(1)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(-1);  //使用哑结点
        ListNode prev = dummy;

        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                prev.next = l1;

                l1 = l1.next;
            }else{
                prev.next = l2;

                l2 = l2.next;
            }

            prev = prev.next;
        }

        prev.next = l1 == null ? l2 : l1;  //这一步对开始值l1 == null || l2 == null容错

        return dummy.next;
    }

    //法二 递归解法
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {

        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }else if(l1.val < l2.val){
            l1.next = mergeTwoLists1(l1.next,l2);

            return l1;
        }else{
            l2.next = mergeTwoLists1(l1,l2.next);

            return l2;
        }
    }



    public static void main(String[] args) {

    }
}
