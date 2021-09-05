package cn.element.leetcode.stage5;

import cn.element.leetcode.common.ListNode;
import cn.element.leetcode.common.ListNodes;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 */
public class L141LinkedListCycle {

    /**
     * 法一: 哈希表
     * 记录每个结点是否被访问过
     * 时间复杂度O(N)
     * 空间复杂度O(N)
     */
    public boolean hasCycle(ListNode head) {

        Set<ListNode> set = new HashSet<>();

        while(head != null){
            if(!set.add(head)){
                return true;
            }

            head = head.next;
        }

        return false;
    }

    /**
     * 法二: 快慢指针法
     * 本方法需要对[Floyd判圆法](又称龟兔赛跑法)有所了解
     * 假设[乌龟]和[兔子]在链表上移动,[兔子跑得快],[乌龟跑得慢]
     * 当[乌龟]和[兔子]从链表上同一个结点开始移动时,如果链表中没有环,那么[兔子]将一直处于[乌龟]的前方
     * 如果该链表中有环,那么[兔子]会先于[乌龟]进入环,并且一直在环内移动,等到[乌龟]进入环时,由于[兔子]
     * 的速度快,它一定会在某个时刻与乌龟相遇,即套了[乌龟]若干圈
     */
    public boolean hasCycle1(ListNode head) {

        if(head == null || head.next == null){
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while(slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }

    public static void main(String[] args) {

        L141LinkedListCycle a = new L141LinkedListCycle();

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

        System.out.println(a.hasCycle1(head.next));
    }
}
