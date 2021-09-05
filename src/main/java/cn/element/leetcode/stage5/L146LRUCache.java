package cn.element.leetcode.stage5;

import java.util.HashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字已经存在，则变更其数据值；如果关键字不存在，
 * 则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶：你是否可以在O(1) 时间复杂度内完成这两种操作？
 *
 * 示例：
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 */
public class L146LRUCache {

    static class DLinkedNode {

        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {

        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Map<Integer, DLinkedNode> map = new HashMap<>();
    private int size;
    private final int capacity;
    private final DLinkedNode head;
    private final DLinkedNode tail;

    /**
     * LRU缓存机制可以通过哈希表辅以双向链表实现,我们用一个哈希表和一个双向链表维护所有在缓存中的键值对
     * 双向链表按照被使用的顺序存储了这些键值对,靠近头部的键值对是最近使用的,而靠近尾部的键值对是最久未使用的
     * 哈希表即为普通的哈希映射(HashMap),通过缓存数据的映射到其在双向链表中的位置
     * 这样以来,我们首先使用哈希表进行定位,找出缓存项在双向链表中的位置,随后将其移动到双向链表的头部
     * 即可在O(1)的时间完成get或者put操作,具体的方法如下
     *      对于 get 操作:首先判断 key 是否存在
     *      如果 key 不存在,则返回 -1
     *      如果 key 存在,则 key 对应的结点是最近使用的结点,通过哈希表定位到该节点在双向链表中的位置
     *      并将其移动到双向链表的头部,最后返回结点的值
     *      对于 put 操作,首先判断 key 是否存在:
     *      如果 key 不存在,使用 key 和 value 创建一个新的结点,在双向链表的头部添加该节点,并将 key 和该节点
     *      添加进哈希表中,然后判断双向链表的节点数是否超出容量,如果超出容量,则删除双向链表的尾部节点,
     *      并删除哈希表中对应的项
     */
    public L146LRUCache(int capacity) {

        this.size = 0;
        this.capacity = capacity;

        head = new DLinkedNode();  // 使用伪头部和伪尾部节点
        tail = new DLinkedNode();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {

        DLinkedNode node = map.get(key);

        if (node == null) {
            return -1;
        }

        this.moveToHead(node);  // 如果key存在,先通过哈希表定位,再移动到头部

        return node.value;
    }


    public void put(int key, int value) {

        DLinkedNode node = map.get(key);

        if (node == null) {
            DLinkedNode newNode = new DLinkedNode(key, value);  // 如果key不存在,创建一个新的节点

            map.put(key, newNode);  // 添加进哈希表

            this.addToHead(newNode);  // 添加至双向链表的头部

            size++;

            if (size > capacity) {
                DLinkedNode tail = this.removeTail();  // 如果超出容量,删除双向链表的尾部节点

                map.remove(tail.key);  // 删除哈希表中对应的项

                size--;
            }
        } else {
            node.value = value;  // 如果key存在,先通过哈希表定位,再修改value,并移动到头部

            this.moveToHead(node);
        }
    }

    private DLinkedNode removeTail() {

        DLinkedNode res = tail.prev;

        this.removeNode(res);

        return res;
    }

    private void addToHead(DLinkedNode node) {

        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void moveToHead(DLinkedNode node) {

        this.removeNode(node);

        this.addToHead(node);
    }

    private void removeNode(DLinkedNode node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {

        L146LRUCache a = new L146LRUCache(2);

        a.put(1, 1); // 缓存是 {1=1}
        a.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(a.get(1));  // 返回 1
        a.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(a.get(2));  // 返回 -1 (未找到)
        a.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(a.get(1));  // 返回 -1 (未找到)
        System.out.println(a.get(3));  // 返回 3
        System.out.println(a.get(4));  // 返回 4
    }
}
