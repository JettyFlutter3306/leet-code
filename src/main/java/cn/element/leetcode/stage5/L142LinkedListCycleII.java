package cn.element.leetcode.stage5;

import cn.element.leetcode.common.ListNode;
import cn.element.leetcode.common.ListNodes;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 * 进阶：
 * 你是否可以使用 O(1) 空间解决此题？
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例2：
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 */
public class L142LinkedListCycleII {

    /**
     * 类似于141题 哈希表法 省略
     * 还是使用快慢指针法
     *
     * 介绍双指针法
     * 我们使用两个指针,fast与slow,它们起始位置都在链表的头部,随后,slow指针每次向后移动一个位置,而fast指针向后移动两个位置
     * 如果链表中有环,则fast指针最终将再次与slow指针在环中相遇
     */
    public ListNode detectCycle(ListNode head) {

        if(head == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null){
            slow = slow.next;

            if(fast.next != null){
                fast = fast.next.next;
            }else{
                return null;
            }

            if(fast == slow){
                ListNode ptr = head;

                while(ptr != slow){
                    ptr = ptr.next;
                    slow = slow.next;
                }

                return ptr;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        L142LinkedListCycleII a = new L142LinkedListCycleII();

        int[] value = {3,2,0,-4};

        ListNode head = ListNodes.createListNode(value);

        ListNode p = head;
        ListNode q = head;

        for (int i = 0; i < value.length; i++) {
            if(i < 2){
                q = q.next;
            }

            p = p.next;
        }

        p.next = q;

        ListNode node = a.detectCycle(head.next);
    }
}
