package cn.element.leetcode.stage5;

import cn.element.leetcode.random_node.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的深拷贝。深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 返回复制链表的头节点。
 * 用一个由n个节点组成的链表来表示输入/输出中的链表。每个节点用一个[val, random_index]表示：
 *
 * val：一个表示Node.val的整数。
 * random_index：随机指针指向的节点索引（范围从0到n-1）；如果不指向任何节点，则为null。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 *
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 *
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 *
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 */
public class L138CopyListWithRandomPointer {

    public Map<Node,Node> map = new HashMap<>();

    /**
     * 回溯 + 哈希表
     *
     * 本题要求对一个特殊的链表进行拷贝,如果是普通的链表,我们可以直接按照遍历的顺序创建链表结点
     * 而本题中因为随机指针的存在,当我们拷贝结点时,[当前结点的随机指针指向的结点]可能还没创建
     * 利用回溯的方式,让每个结点拷贝操作相互独立,对于当前结点,我们首先要进行拷贝,然后我们进行[当前结点的后继结点]
     * 和当前结点的随机指针指向的结点拷贝,拷贝完成之后将创建的指针返回,即可完成当前结点的两指针的赋值
     */
    public Node copyRandomList(Node head) {

        if(head == null){
            return null;
        }

        if(!map.containsKey(head)){
            Node headNew = new Node(head.val);

            map.put(head, headNew);

            headNew.next = copyRandomList(head.next);

            headNew.random = copyRandomList(head.random);
        }

        return map.get(head);
    }

    public static void main(String[] args) {

        L138CopyListWithRandomPointer a = new L138CopyListWithRandomPointer();

    }
}
