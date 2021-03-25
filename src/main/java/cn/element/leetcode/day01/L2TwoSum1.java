package cn.element.leetcode.day01;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 */
public class L2TwoSum1 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = null,tail = null;//定义一个指向头结点的引用和一个指向尾结点的引用
        int carry = 0;                  //定义进位

        while(l1 != null || l2 != null){
            int n1 = l1 != null ? l1.val : 0;   //获取l1链表的对应位置的元素
            int n2 = l2 != null ? l2.val : 0;   //获取l2链表的对应位置的元素

            int sum = n1 + n2 + carry;

            if(head == null){
                head = tail = new ListNode(sum % 10);
            }else{
                tail.next = new ListNode(sum % 10);

                tail = tail.next;
            }

            carry = sum / 10;       //更新进位变量

            if(l1 != null){
                l1 = l1.next;   //将l1向后挪一个单位
            }

            if(l2 != null){
                l2 = l2.next;  //将l2向后挪一个单位
            }
        }

        if(carry > 0){
            tail.next = new ListNode(carry);    //处理最高位的进位,进位会有溢出
        }

        return head;    //返回头结点
    }



    public static void main(String[] args) {

        //定义342
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        //定义465
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode listNode = addTwoNumbers(l1, l2);

        System.out.println(listNode);





    }
}

//定义一个单链表
class ListNode{

    int val;

    ListNode next;

    ListNode(){

    }

    ListNode(int val){
        this.val = val;
    }

    ListNode(int val,ListNode next){
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {

        List<Integer> list = new ArrayList<>();

        for (ListNode p = this; p != null ; p = p.next) {
            list.add(p.val);
        }

        return list.toString();
    }
}
