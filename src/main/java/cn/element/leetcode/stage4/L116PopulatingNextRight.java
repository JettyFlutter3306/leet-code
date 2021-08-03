package cn.element.leetcode.stage4;

import cn.element.leetcode.common.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有next 指针都被设置为 NULL。
 *
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 示例：
 *
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * 序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 */
public class L116PopulatingNextRight {

    public Node connect(Node root) {

        if(root == null){
            return root;
        }

        Queue<Node> queue = new LinkedList<>();  //初始化队列同时将第一层结点加入队列中,即根结点
        queue.add(root);

        while(!queue.isEmpty()){  //外层的while循环迭代的层数
            int size = queue.size();  //记录当前队列的大小

            for (int i = 0; i < size; i++) {  //遍历这一层所有的结点
                Node node = queue.poll();  //从队首取出元素

                if(i < size - 1){  //连接
                    node.next = queue.peek();
                }

                if(node.left != null){  //拓展下一层结点
                    queue.add(node.left);
                }

                if(node.right != null){
                    queue.add(node.right);
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {

        L116PopulatingNextRight a = new L116PopulatingNextRight();


    }
}
