package cn.element.leetcode.stage5;

import cn.element.leetcode.graph_node.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你无向连通图中一个节点的引用，请你返回该图的深拷贝（克隆）。
 *
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 * 测试用例格式：
 *
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），
 * 以此类推。该图在测试用例中使用邻接列表表示。
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将给定节点的拷贝作为对克隆图的引用返回。
 *
 * 示例 1：
 * 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
 * 输出：[[2,4],[1,3],[2,4],[1,3]]
 * 解释：
 * 图中有 4 个节点。
 * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 * 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 *
 * 示例 2：
 * 输入：adjList = [[]]
 * 输出：[[]]
 * 解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
 *
 * 示例 3：
 * 输入：adjList = []
 * 输出：[]
 * 解释：这个图是空的，它不含任何节点。
 *
 * 示例 4：
 * 输入：adjList = [[2],[1]]
 * 输出：[[2],[1]]
 */
public class L133CloneGraph {

    private final Map<Node,Node> visited = new HashMap<>();

    /**
     * 考虑使用深度优先搜索
     * 对于本题而言,要明确图的深拷贝是在做什么,对于一张图而言,它的深拷贝即为构建一张与原图结构,值均一样的图
     * 但是其中的结点不再是原来图中的结点,因此为了深拷贝整张图,我们需要知道整张图的结构以及对应结点的值
     */
    public Node cloneGraph(Node node) {

        if(node == null){
            return node;
        }

        if(visited.containsKey(node)){  //如果该节点已经被访问过了,则直接从哈希表中取出对应的克隆结点返回
            return visited.get(node);
        }

        Node cloneNode = new Node(node.val, new ArrayList<>());  //克隆结点,注意到为了深拷贝我们不会克隆它的邻居的列表

        visited.put(node, cloneNode);  //哈希表存储

        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }

    public static void main(String[] args) {

        L133CloneGraph a = new L133CloneGraph();
    }
}
