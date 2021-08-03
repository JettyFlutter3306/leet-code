package cn.element.leetcode.stage4;

import cn.element.leetcode.common.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树
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
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 示例：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * 序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 */
public class L117PopulatingNextRight {

    /**
     * 层次遍历
     */
    public Node connect(Node root) {

        if(root == null){
            return null;
        }

        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()){
            int n = queue.size();

            Node last = null;

            for (int i = 1; i <= n; i++) {
                Node f = queue.poll();

                if(f.left != null){
                    queue.offer(f.left);
                }

                if(f.right != null){
                    queue.offer(f.right);
                }

                if(i != 1){
                    last.next = f;
                }

                last = f;
            }
        }

        return root;
    }

    public static void main(String[] args) {

        L117PopulatingNextRight a = new L117PopulatingNextRight();
    }
}
